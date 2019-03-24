package com.epam.travelagency.web.controller;

import com.epam.travelagency.entity.User;
import com.epam.travelagency.enumeration.Role;
import com.epam.travelagency.service.UserService;
import com.epam.travelagency.service.exception.LoginNotUniqueException;
import com.epam.travelagency.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SignUpController {

    private final UserService service;

    @Autowired
    public SignUpController(UserService service) {
        this.service = service;
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/process_signup")
    public String processSignUp(@Valid @ModelAttribute("userForm") UserDTO user,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (!result.hasErrors()) {
            try {
                service.add(user.getLogin(), user.getPassword());
                return "redirect:/login";
            } catch (LoginNotUniqueException e) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Entered login is not unique");
                if (user.getLogin() != null) {
                    redirectAttributes.addAttribute("login", user.getLogin());
                }
                return "redirect:/signup";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Entered data is not valid");
            if (user.getLogin() != null) {
                redirectAttributes.addAttribute("login", user.getLogin());
            }
            return "redirect:/signup";
        }
    }
}
