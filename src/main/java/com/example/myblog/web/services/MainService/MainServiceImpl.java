package com.example.myblog.web.services.MainService;

import com.example.myblog.web.entities.Category;
import com.example.myblog.web.entities.Post;
import com.example.myblog.web.entities.SiteInfo;
import com.example.myblog.web.entities.Slider;
import com.example.myblog.web.repositores.CategoryRepositories.CategoryRepository;
import com.example.myblog.web.repositores.PostRepositories.PostRepository;
import com.example.myblog.web.repositores.SiteInfoRepositories.SiteInfoRepository;
import com.example.myblog.web.repositores.SliderRepositories.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private SiteInfoRepository siteInfoRepository;

    @Autowired
    private SliderRepository sliderRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public MainServiceImpl(SiteInfoRepository siteInfoRepository) {
        this.siteInfoRepository = siteInfoRepository;
    }

    @Override
    public void loadSiteSettings() {
        Random rand = new Random();
        List<SiteInfo> isSiteInfoInserted = siteInfoRepository.findAll();
        if(isSiteInfoInserted.size()==0) {
            SiteInfo siteInfo = new SiteInfo();
            siteInfo.setBlogId(rand.nextInt());
            siteInfo.setBlogName("My blog name");
            siteInfo.setAuthorName("Author name!");
            siteInfo.setCssPath("style.css");
            siteInfo.setFooterText("My footer text!");
            siteInfo.setSliderEnabled(true);
            siteInfo.setCategoriesMenuEnabled(true);
            siteInfo.setSearchMenuEnabled(true);
            siteInfoRepository.save(siteInfo);
            //default slider 1
            Slider slider = new Slider();
            slider.setTitle("Hello!");
            slider.setPhotoText("Welcome to the blog! This is default slider picture.");
            slider.setPhotoPath("/img/slider/slider1.jpg");
            sliderRepository.save(slider);
            //default slider 2
            Slider slider2 = new Slider();
            slider2.setTitle("Hello!");
            slider2.setPhotoText("Welcome to the blog! This is default slider picture. (2)");
            slider2.setPhotoPath("/img/slider/slider3.jpg");
            sliderRepository.save(slider2);
            //default category
            Category category = new Category();
            category.setName("Обща категория");
            category.setDescription("Това е обща категория по подразбиране.");
            categoryRepository.save(category);
        }
    }

    @Override
    public void getSiteInfo(Model model) {
        SiteInfo siteInfo = new SiteInfo();
        for (SiteInfo i : siteInfoRepository.findAll()) {
            siteInfo = i;
        }
        List<Slider> sliderStartFrom2 = sliderRepository.findAll();

        model.addAttribute("blogName", siteInfo.getBlogName());
        model.addAttribute("blogAuthor", siteInfo.getAuthorName());
        model.addAttribute("footerText", siteInfo.getFooterText());
        model.addAttribute("sliderStatus", siteInfo.isSliderEnabled());
        model.addAttribute("sliderPicsPlusOne", sliderRepository.findAll());
    }

    @Override
    public void loadBlogPosts(Model model) {
        List<Post> allPosts = postRepository.findAll();
        List<Post> cuttedPosts = new ArrayList<>();
        for (Post post : allPosts) {
            String postNewContent = "";
            String[] postContent = post.getText().split("\\s+");
            if(postContent.length>=80){
                for (int i = 0; i < 80; i++) {
                    postNewContent += " "+postContent[i];
                }
            } else {
                postNewContent = post.getText();
            }
            postNewContent+="...";
            String postOriginalText = post.getText();
            model.addAttribute("postOriginalSize", postOriginalText.length());
            post.setText(postNewContent);
            cuttedPosts.add(post);
        }
        Collections.reverse(cuttedPosts);
        model.addAttribute("allPosts", cuttedPosts);
    }

    @Override
    public void loadPostPage(Model model, Long id) {
        Post post = postRepository.getOne(id);
        model.addAttribute("post", post);
    }
}
