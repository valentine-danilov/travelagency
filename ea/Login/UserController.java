package com.epam.travelagency.web.controller;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.repository.specification.impl.postgre.review.ByUserId;
import com.epam.travelagency.service.ReviewService;
import com.epam.travelagency.service.TourService;
import com.epam.travelagency.service.UserService;
import com.epam.travelagency.web.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

@Controller
public class UserController {

    private final TourService tourService;

    private final ReviewService reviewService;

    private final UserService userService;

    @Autowired
    public UserController(TourService tourService, ReviewService reviewService, UserService userService) {
        this.tourService = tourService;
        this.reviewService = reviewService;
        this.userService = userService;
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

    @RolesAllowed({"ROLE_MEMBER", "ROLE_ADMIN"})
    @GetMapping("/cart")
    public String getCartPage(Authentication authentication, ModelMap model) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", user);
        if (nonNull(user.getCart())) {
            model.addAttribute("totalMoneyAmount", user.getCart().
                    stream()
                    .mapToDouble(tour -> tour.getCost().doubleValue())
                    .sum());
        }
        return "cart";
    }

    @RolesAllowed({"ROLE_MEMBER", "ROLE_ADMIN"})
    @PostMapping("/cart/process_purchase")
    public String processPurchase(Authentication authentication, Double totalMoneyAmount, RedirectAttributes redirectAttributes) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Integer userId = user.getId();
        user.getCart().forEach(t -> userService.buyTour(userId, t.getId()));
        user.getCart().clear();
        redirectAttributes.addFlashAttribute("successMessage",
                String.format("Purchase completed successfully. Total money spent: %f", totalMoneyAmount));
        return "redirect:/cart";
    }

}
