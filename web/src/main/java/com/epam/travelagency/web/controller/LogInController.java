package com.epam.travelagency.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LogInController {

    @GetMapping({"/","/login"})
    public String showForm(@RequestParam(name = "error", required = false) String error, ModelMap model) {
        if (error != null) {
            model.addAttribute("loginError", "Invalid login or password");
        }
        return "login";
    }
}