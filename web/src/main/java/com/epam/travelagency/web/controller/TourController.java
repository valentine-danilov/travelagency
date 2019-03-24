package com.epam.travelagency.web.controller;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.web.pagination.Pagination;
import com.epam.travelagency.repository.specification.TourSpecification;
import com.epam.travelagency.repository.specification.impl.postgre.tour.*;
import com.epam.travelagency.service.TourService;
import com.epam.travelagency.web.dto.TourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TourController {

    private final TourService service;

    @Autowired
    public TourController(TourService service) {
        this.service = service;
    }

    @GetMapping("/tours")
    public String searchTour(Pagination<Tour> pagination, TourDTO tour,
                             ModelMap modelMap) {
        List<TourSpecification> specifications = determineSpecifications(tour);
        Long pageNumber = service.getPageNumber(2);
        pagination.setPageNumber(pageNumber);
        pagination.setPageSize(2);
        if(pagination.getPage()==null){
            pagination.setPage(1);
        }
        List<Tour> tours = service
                .findAllBySpecificationWithOffsetAndMaxSize
                        (specifications, pagination.getOffset(), pagination.getPageSize());
        pagination.setContent(tours);
        modelMap.addAttribute("pagination", pagination);
        return "home";
    }

    @GetMapping("/tour")
    public String tourById(@RequestParam Integer id, ModelMap model){
        Tour tour = service.readById(id);
        model.addAttribute("tour", tour);
        model.addAttribute("reviews", tour.getReviews());
        return "/tour";
    }

    private List<TourSpecification> determineSpecifications(TourDTO tour) {
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
