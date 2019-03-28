package com.epam.travelagency.web.controller;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.service.ReviewService;
import com.epam.travelagency.service.TourService;
import com.epam.travelagency.web.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    private final TourService tourService;

    @Autowired
    public ReviewController(ReviewService reviewService, TourService tourService) {
        this.reviewService = reviewService;
        this.tourService = tourService;
    }

    @PostMapping("/review/add")
    public String addReview(String text, Integer tourId, Authentication authentication) {
        if(authentication==null){
            return String.format("redirect:/tour?id=%d", tourId);
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = User.builder()
                .login(userDetails.getLogin())
                .password(userDetails.getPassword())
                .role(userDetails.getRole())
                .build();
        user.setId(userDetails.getId());
        Tour tour = tourService.readById(tourId);
        reviewService.add(new Date(), text, user, tour);
        return String.format("redirect:/tour?id=%d", tourId);
    }
}
