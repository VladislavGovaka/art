package com.example.collectionsApp.controllers.main;

import com.example.collectionsApp.helperСlasses.search.Search;
import com.example.collectionsApp.models.collection.Item;
import com.example.collectionsApp.service.cloudinary.CloudinaryService;
import com.example.collectionsApp.service.collection.CollectionService;
import com.example.collectionsApp.service.collection.ItemService;
import com.example.collectionsApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MainPageController {

    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CollectionService collectionService;

    @Autowired
    CloudinaryService cloudinaryService;

    private boolean flagShowCollectionsAll = false;

    private List<Item> itemSearch;



    @GetMapping("/main")
    public String main(@ModelAttribute("item") Item item, Model model){
        model.addAttribute("isAuthentication", userService.isAuthentication());
        model.addAttribute("authenticationName", userService.getAuthenticationName());
        model.addAttribute("isAdmin", userService.isAdmin());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("collectionsTop", collectionService.findTop());
        model.addAttribute("allCollections", collectionService.findAllSort());
        model.addAttribute("flagShowCollectionsAll", flagShowCollectionsAll);

        if(itemSearch != null)
            model.addAttribute("items", itemSearch);
        else
            model.addAttribute("items", itemService.findAllSortByDate());


        return "main";
    }


    @PostMapping(value = "/main", params = "action=changeCollectionsAll")
    public String changeFlagCollectionsAll(){
        if(flagShowCollectionsAll)
            flagShowCollectionsAll = false;
        else
            flagShowCollectionsAll = true;
        return "redirect:/main";
    }

    @PostMapping(value = "/main", params = "action=search")
    public String search(@RequestParam("search") String key){

        if(key.length() != 0)
            itemSearch = Search.searchItems(key, itemService.findAll());
        else
            itemSearch = null;

        return "redirect:/main";
    }

//    @PostMapping(value = "/main", params = "action=allItems")
//    public String allItems(){
//        itemSearch = null;
//        return "redirect:/main";
//    }


}
