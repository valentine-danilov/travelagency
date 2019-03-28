package com.epam.travelagency.web.controller;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.service.TourService;
import com.epam.travelagency.web.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
public class HomeController {

    private final TourService tourService;

    @Autowired
    public HomeController(TourService tourService) {
        this.tourService = tourService;
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_MEMBER"})
    @GetMapping("/home")
    public String userHome(Authentication authentication, ModelMap model) {
        List<Tour> tours = tourService.readAll();
        model.addAttribute("tours", tours);

        if (authentication == null) {
            return "tours";
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails);
        return "tours";
    }
}
