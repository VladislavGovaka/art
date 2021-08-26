package com.example.collectionsApp.controllers.login_registration;

import com.example.collectionsApp.models.user.User;
import com.example.collectionsApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "registration";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("isAuthentication", userService.isAuthentication());
        model.addAttribute("authenticationName", userService.getAuthenticationName());
        return "login";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "redirect:/login";

        userService.add(user);
        return "redirect:/login";
    }



}
