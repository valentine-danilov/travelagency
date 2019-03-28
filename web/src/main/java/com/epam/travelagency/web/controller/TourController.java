package com.epam.travelagency.web.controller;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.enumeration.TourType;
import com.epam.travelagency.service.CountryService;
import com.epam.travelagency.service.HotelService;
import com.epam.travelagency.web.dto.TourDTO;
import com.epam.travelagency.web.exception.BadRequestException;
import com.epam.travelagency.web.pagination.Pagination;
import com.epam.travelagency.repository.specification.TourSpecification;
import com.epam.travelagency.repository.specification.impl.postgre.tour.*;
import com.epam.travelagency.service.TourService;
import com.epam.travelagency.web.dto.TourSearchDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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
        Long pageNumber = tourService.getPageNumber(2);
        pagination.setPageNumber(pageNumber);
        pagination.setPageSize(2);

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
    public String tourById(@RequestParam Integer id, ModelMap model) {
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
    public String processAdd(@ModelAttribute("tour-form") TourDTO tour) {
        tourService.add("as", java.sql.Date.valueOf(tour.getDate()),
                tour.getDuration(), tour.getDescription(),
                new BigDecimal(tour.getCost()),
                TourType.valueOf(tour.getTourType()),
                hotelService.readById(tour.getHotel()),
                countryService.readById(tour.getCountry()));
        return "redirect:/tours";
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
