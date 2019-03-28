package com.epam.travelagency.web.controller;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.repository.specification.impl.postgre.review.ByUserId;
import com.epam.travelagency.service.ReviewService;
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
public class UserController {

    private final TourService tourService;

    private final ReviewService reviewService;

    @Autowired
    public UserController(TourService tourService, ReviewService reviewService) {
        this.tourService = tourService;
        this.reviewService = reviewService;
    }

    /*@GetMapping("/profile/${user-login}")
    public String getProfilePage(@PathVariable("user-login") String login, Authentication authentication, ModelMap model){
        if(authentication!=null){
            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

        }
    }*/

    @RolesAllowed({"ROLE_MEMBER", "ROLE_ADMIN"})
    @GetMapping("/profile")
    public String getAuthorisedUserProfilePage(Authentication authentication, ModelMap model) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        List<Tour> tours = tourService.findAllByUser(user.getId());
        List<Review> reviews = reviewService
                .findAllBySpecification(List.of(new ByUserId(user.getId())));
        model.addAttribute("user", user);
        model.addAttribute("tours", tours);
        model.addAttribute("reviews", reviews);
        return "profile";
    }
}
