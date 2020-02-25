package com.example.myblog.web.controllers;

import com.example.myblog.web.entities.Category;
import com.example.myblog.web.entities.Post;
import com.example.myblog.web.entities.SiteInfo;
import com.example.myblog.web.entities.Slider;
import com.example.myblog.web.services.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    //slider settings

    @RequestMapping(value = "/sliderOnOff", method = RequestMethod.POST)
    public String sliderOnOff(SiteInfo siteInfo, @RequestParam boolean isSliderEnabled) {
        adminService.turnSliderOnOrOff(siteInfo, isSliderEnabled);
        return "redirect:/admin-personalisation?slideshowUpdated";
    }

    @RequestMapping(value = "/sliderUpload", method = RequestMethod.POST)
    public String uploadSlider(@RequestParam("files") MultipartFile[] files, SiteInfo siteInfo, Slider slider) throws IOException {
        adminService.uploadSlider(files, siteInfo, slider);
        return "redirect:/admin-personalisation?sliderUploaded";
    }

    @RequestMapping(value = "/removeSlider")
    public String removeSlider(@RequestParam("picId") Long id) {
        adminService.deleteSliderPic(id);
        return "redirect:/admin-personalisation?sliderDeleted";
    }

    @RequestMapping(value = "/sliderUpdate", method = RequestMethod.POST)
    public String updateSlider(@RequestParam("picId") Long id, Slider slider) {
        adminService.updateSlider(id, slider);
        return "redirect:/admin-personalisation?slideshowUpdated";
    }

    //left menus settings
    //todo da napravq menutata sled kato napravq postovete oti ima da se pravqt kategorii

    //admin personalisation settings end

    //posts and categories settings start
    @RequestMapping(value = "/admin-posts", method = RequestMethod.GET)
    public String adminPosts(Model model) {
        adminService.getSiteInfo(model);
        return "admin-posts";
    }
    //posts and categories settings end

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String newPost(@RequestParam("files") MultipartFile[] files, Post post) throws IOException {
        adminService.newPost(files, post);
        return "redirect:/admin-posts?postOk";
    }

    @RequestMapping(value = "/newCategory", method = RequestMethod.POST)
    public String createCategory(Category category) {
        adminService.newCategory(category);
        return "redirect:/admin-posts?categoryCreated";
    }

    @RequestMapping(value = "/admin-category-posts", method = RequestMethod.GET)
    public String showCategoryPosts(@RequestParam("id") long id, Model model) {
        adminService.getSiteInfo(model);
        adminService.showCategoryPost(id, model);
        return "admin-category-posts";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.POST)
    public String updatePost(Post post, @RequestParam("postId") long id){
        adminService.editPost(post, id);
        return "redirect:/admin-posts?postEdited";
    }
    @RequestMapping(value = "/removePost")
    public String removePost(@RequestParam("postId") Long id){
        adminService.removePost(id);
        return "redirect:/admin-posts?postDeleted";
    }

    @RequestMapping(value = "/deleteAllPosts")
    public String deleteAllPosts(){
        adminService.deleteAllPosts();
        return "redirect:/admin-posts?allPostsDeleted";
    }
}
