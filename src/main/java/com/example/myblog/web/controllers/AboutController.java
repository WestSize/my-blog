package com.example.myblog.web.controllers;

import com.example.myblog.web.services.MainService.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutController {
    @Autowired
    private MainService mainService;

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model){
        mainService.getSiteInfo(model);
        return "about";
    }
}
