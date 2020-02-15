package com.example.myblog.web.controllers;

import com.example.myblog.web.entities.SiteInfo;
import com.example.myblog.web.services.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    //admin home page
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String AdminPanelHome(Model model) {
        adminService.getSiteInfo(model);
        return "admin-home";
    }

    @RequestMapping(value = "/changeBlogName", method = RequestMethod.POST)
    public String ChangeBlogName(SiteInfo siteInfo) {
        if (siteInfo.getBlogName().equals("")) {
            return "redirect:/admin?emptyBlogName";
        } else {
            adminService.updateBlogName(siteInfo);
            return "redirect:/admin?nameUpdated";
        }
    }

    @RequestMapping(value = "/changeBlogAuthor", method = RequestMethod.POST)
    public String changeBlogAuthor(SiteInfo siteInfo) {
        if (siteInfo.getAuthorName().equals("")) {
            return "redirect:/admin?emptyAuthorName";
        } else {
            adminService.updateBlogAuthor(siteInfo);
            return "redirect:/admin?authorUpdated";
        }
    }

    @RequestMapping(value = "/changeFooter", method = RequestMethod.POST)
    public String changeFooter(SiteInfo siteInfo) {
        if (siteInfo.getFooterText().equals("")) {
            return "redirect:/admin?emptyFooter";
        } else {
            adminService.updateBlogFooter(siteInfo);
            return "redirect:/admin?footerUpdated";
        }
    }

    //admin personalisation page
    @RequestMapping(value = "/admin-personalisation", method = RequestMethod.GET)
    public String adminPersonalisation(Model model) {
        adminService.getSiteInfo(model);
        return "admin-personalisation";
    }

    @RequestMapping(value = "/sliderOnOff", method = RequestMethod.POST)
    public String sliderOnOff(SiteInfo siteInfo, @RequestParam boolean isSliderEnabled){
        adminService.turnSliderOnOrOff(siteInfo, isSliderEnabled);
        return "redirect:/admin-personalisation?slideshowUpdated";
    }
}
