package com.example.collectionsApp.controllers.admin;

import com.example.collectionsApp.helperСlasses.checkbox.CheckBoxUtil;
import com.example.collectionsApp.models.user.User;
import com.example.collectionsApp.service.collection.CollectionService;
import com.example.collectionsApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminsController {

    @Autowired
    private UserService userService;

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/{nameUser}")
    public String adminPage(@PathVariable("nameUser") String name, Model model){

        if(name.equals(userService.getAuthenticationName())){
            model.addAttribute("isAuthentication", userService.isAuthentication());
            model.addAttribute("authenticationName", userService.getAuthenticationName());
            model.addAttribute("authenticationUser", userService.getAuthenticationUser());
            model.addAttribute("users", userService.findAll());
            model.addAttribute("checkBoxUtil", new CheckBoxUtil());
            model.addAttribute("sizeAllCollections", collectionService.sizeAllCollections());
            model.addAttribute("sizeAllItems", collectionService.sizeAllItems());
            model.addAttribute("sizeAllUsers", userService.sizeAllUsers());
            return "admin";
        }
        return "redirect:/main";
    }

    @PostMapping( value = "/{nameUser}", params = "action=delete")
    public String deleteUser(@PathVariable("nameUser") String name,
                             @ModelAttribute(value="checkBoxUtil") CheckBoxUtil checkBoxUtil,
                             Model model) {

        List<User> checkedItems = checkBoxUtil.getCheckedItems();
        for(User user : checkedItems) {
            userService.deleteById(user.getId());
        }
        return "redirect:/admins/"+name;
    }

    @PostMapping( value = "/{nameUser}", params = "action=setRoleUser")
    public String setRoleUser(@PathVariable("nameUser") String name,
                             @ModelAttribute(value="checkBoxUtil") CheckBoxUtil checkBoxUtil,
                             Model model) {

        userService.setRoleUser(checkBoxUtil.getCheckedItems());

        return  "redirect:/admins/"+name;
    }

    @PostMapping( value = "/{nameUser}", params = "action=setRoleAdmin")
    public String setRoleAdmin(@PathVariable("nameUser") String name,
                              @ModelAttribute(value="checkBoxUtil") CheckBoxUtil checkBoxUtil,
                              Model model) {

        userService.setRoleAdmin(checkBoxUtil.getCheckedItems());

        return  "redirect:/admins/"+name;
    }
}