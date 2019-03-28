package com.epam.travelagency.web.controller;

import com.epam.travelagency.service.UserService;
import com.epam.travelagency.service.exception.LoginNotUniqueException;
import com.epam.travelagency.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SignUpController {

    private final UserService service;

    private final MessageSource messageSource;

    @Autowired
    public SignUpController(UserService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
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
            for (Object object : result.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    String message = messageSource.getMessage(fieldError, null);
                    redirectAttributes.addFlashAttribute("errorMessage",
                            message);
                    if (user.getLogin() != null) {
                        redirectAttributes.addAttribute("login", user.getLogin());
                    }
                }
            }

            return "redirect:/signup";
        }
    }
}
