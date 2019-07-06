package com.epam.travelagency.web.controller;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.enumeration.Role;
import com.epam.travelagency.enumeration.TourType;
import com.epam.travelagency.repository.impl.postgre.TourRepository;
import com.epam.travelagency.service.CountryService;
import com.epam.travelagency.service.HotelService;
import com.epam.travelagency.web.dto.TourDTO;
import com.epam.travelagency.web.exception.BadRequestException;
import com.epam.travelagency.web.pagination.Pagination;
import com.epam.travelagency.repository.specification.TourSpecification;
import com.epam.travelagency.repository.specification.impl.postgre.tour.*;
import com.epam.travelagency.service.TourService;
import com.epam.travelagency.web.dto.TourSearchDTO;
import com.epam.travelagency.web.security.details.UserDetailsImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Controller
public class TourController {

    private org.slf4j.Logger LOG = LoggerFactory.getLogger(TourController.class);

    private final TourService tourService;

    private final HotelService hotelService;

    private final CountryService countryService;

    @Autowired
    public TourController(TourService service,
                          HotelService hotelService,
                          CountryService countryService) {
        this.tourService = service;
        this.hotelService = hotelService;
        this.countryService = countryService;
    }

    @GetMapping("/tours")
    public String searchTour(Pagination<Tour> pagination,
                             TourSearchDTO tour,
                             ModelMap modelMap)
            throws Exception {
        List<TourSpecification> specifications = determineSpecifications(tour);
        Long pageNumber = tourService.getPageNumber(10, specifications);
        pagination.setPageNumber(pageNumber);
        pagination.setPageSize(10);

        if (pagination.getPage() == null) {
            pagination.setPage(1);
        }

        if (pagination.getPage() < 1) {
            throw new BadRequestException("Requested page can't be loaded.");
        }

        List<Tour> tours = tourService
                .findAllBySpecificationWithOffsetAndMaxSize
                        (specifications, pagination.getOffset(), pagination.getPageSize());
        pagination.setContent(tours);
        modelMap.addAttribute("pagination", pagination);
        return "tours";
    }

    @GetMapping("/tour")
    public String tourById(@RequestParam Integer id, ModelMap model, Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        if (user.getRole().equals(Role.ROLE_ADMIN)) {
            List<Country> countries = countryService.readAll();
            List<Hotel> hotels = hotelService.readAll();
            model.addAttribute("countries", countries);
            model.addAttribute("hotels", hotels);
            model.addAttribute("tourTypes", List.of(TourType.values()));
        }
        Tour tour = tourService.readById(id);
        model.addAttribute("tour", tour);
        model.addAttribute("reviews", tour.getReviews());
        return "/tour";
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/tour/add")
    public String getAddTourForm(ModelMap model) {
        model.addAttribute("hotels", hotelService.readAll());
        model.addAttribute("countries", countryService.readAll());
        model.addAttribute("tourTypes", List.of(TourType.values()));
        return "tourAddForm";
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/tour/process_adding")
    public String processAdd(TourDTO tour) {
        tourService.add("as", java.sql.Date.valueOf(tour.getDate()),
                tour.getDuration(), tour.getDescription(),
                new BigDecimal(tour.getCost()),
                TourType.valueOf(tour.getTourType()),
                hotelService.readById(tour.getHotel()),
                countryService.readById(tour.getCountry()));
        return "redirect:/tours";
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/tour/edit")
    public String processEditing(Integer id, TourDTO tour) {
        tourService.update(id, "test1.jpg", java.sql.Date.valueOf(tour.getDate()),
                tour.getDuration(), tour.getDescription(),
                new BigDecimal(tour.getCost()),
                TourType.valueOf(tour.getTourType()),
                hotelService.readById(tour.getHotel()),
                countryService.readById(tour.getCountry()));
        return "redirect:/tour?id=" + id;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/tour/process_deleting")
    public String processDeleting(Integer id,
                                  RedirectAttributes redirectAttributes) {
        try {
            tourService.deleteById(id);
            return "redirect:/tours";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "You can't delete this tour because it still owned by some user");
            return "redirect:/tours";
        }

    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_MEMBER"})
    @GetMapping("/tour/process_adding_to_cart")
    public String addToCart(@RequestParam Integer id,
                            Authentication authentication,
                            RedirectAttributes redirectAttributes) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Tour tour = tourService.readById(id);
        if (tourService.findAllByUser(userDetails.getId()).contains(tour)) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "You already own this tour");
            return "redirect:/tours";
        }
        if (nonNull(userDetails.getCart())) {
            if (userDetails.getCart().stream().anyMatch(t -> Objects.equals(t.getId(), id))) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        String.format("Tour with id=%d already added to cart", id));
                return "redirect:/tours";
            }
            userDetails.getCart().add(tour);
        } else {
            userDetails.setCart(new HashSet<>());
            userDetails.getCart().add(tour);
        }
        redirectAttributes.addFlashAttribute("successMessage",
                String.format("Tour with id=%d successfully added to your cart!", id));
        return "redirect:/tours";
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_MEMBER"})
    @GetMapping("/tour/process_removing_from_cart")
    public String removeFromCart(@RequestParam Integer id,
                                 Authentication authentication,
                                 RedirectAttributes redirectAttributes) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (nonNull(userDetails)) {
            Tour tourToRemove = userDetails.getCart().stream().findAny().filter(tour -> tour.getId().equals(id)).get();
            userDetails.getCart().remove(tourToRemove);
            redirectAttributes.addFlashAttribute("successMessage",
                    String.format("Tour with id=%d successfully removed from your cart!", id));
        }
        return "redirect:/cart";
    }

    private List<TourSpecification> determineSpecifications(TourSearchDTO tour) {
        List<TourSpecification> specifications = new ArrayList<>();
        if (tour.getCost() != null && !tour.getCost().trim().equals("")) {
            specifications.add(new ByTourCost(new BigDecimal(tour.getCost())
                    .setScale(3, RoundingMode.HALF_UP),
                    "<"));
        }
        if (tour.getStars() != null) {
            specifications.add(new ByHotelStars(tour.getStars(), "="));
        }
        if (tour.getDuration() != null) {
            specifications.add(new ByTourDuration(tour.getDuration(), "<"));
        }
        if (tour.getDate() != null && !tour.getDate().trim().equals("")) {
            specifications.add(new ByTourDate(java.sql.Date.valueOf(tour.getDate())));
        }
        if (tour.getCountry() != null && !tour.getCountry().trim().equals("")) {
            specifications.add(new ByCountry(tour.getCountry()));
        }
        return specifications;
    }
}
