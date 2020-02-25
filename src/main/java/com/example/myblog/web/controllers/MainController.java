package com.example.myblog.web.controllers;

import com.example.myblog.web.services.MainService.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        mainService.loadSiteSettings();
        mainService.loadBlogPosts(model);
        mainService.getSiteInfo(model);
        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home2(Model model){
        mainService.getSiteInfo(model);
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
        mainService.getSiteInfo(model);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/admin";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/read-post", method = RequestMethod.GET)
    public String readPostPage(Model model, @RequestParam("id") long id){
        mainService.getSiteInfo(model);
        mainService.loadPostPage(model, id);
        return "read-post";
    }
}
