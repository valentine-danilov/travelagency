package com.epam.travelagency.web.controller;

import com.epam.travelagency.web.security.details.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LogInController {
    @GetMapping("/login")
    public String showForm() {
        return "login";
    }

    @GetMapping("/user/{user-id}/profile")
    public String userProfileById(@PathVariable("user-id") Integer userId, Authentication authentication, ModelMap model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails);
        return "profile";
    }

}