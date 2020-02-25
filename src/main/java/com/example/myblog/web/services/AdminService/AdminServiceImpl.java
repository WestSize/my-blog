package com.example.myblog.web.services.AdminService;

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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private SiteInfoRepository siteInfoRepository;
    @Autowired
    private SliderRepository sliderRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public static String uploadSliderDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img/slider";
    public static String uploadPostDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img/posts";

    public AdminServiceImpl(SiteInfoRepository siteInfoRepository) {
        this.siteInfoRepository = siteInfoRepository;
    }

    @Override
    public void getSiteInfo(Model model) {
        SiteInfo siteInfo = new SiteInfo();
        for (SiteInfo i : siteInfoRepository.findAll()) {
            siteInfo = i;
        }

        model.addAttribute("blogName", siteInfo.getBlogName());
        model.addAttribute("blogAuthor", siteInfo.getAuthorName());
        model.addAttribute("footerText", siteInfo.getFooterText());
        model.addAttribute("sliderEnabled", siteInfo.isSliderEnabled());
        model.addAttribute("sliderPics", sliderRepository.findAll());
        model.addAttribute("sliderPicsSize", sliderRepository.findAll().size());
        model.addAttribute("sliderPicsPlusOne", sliderRepository.findAll());
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allPosts", postRepository.findAll());

    }

    @Override
    public void updateBlogName(SiteInfo siteInfo) {
        SiteInfo siteInfoOld = new SiteInfo();
        for (SiteInfo i : siteInfoRepository.findAll()) {
            siteInfoOld = i;
        }
        siteInfoOld.setBlogName(siteInfo.getBlogName());
        siteInfoRepository.save(siteInfoOld);
    }

    @Override
    public void updateBlogAuthor(SiteInfo siteInfo) {
        SiteInfo siteInfoOld = new SiteInfo();
        for (SiteInfo i : siteInfoRepository.findAll()) {
            siteInfoOld = i;
        }
        siteInfoOld.setAuthorName(siteInfo.getAuthorName());
        siteInfoRepository.save(siteInfoOld);
    }

    @Override
    public void updateBlogFooter(SiteInfo siteInfo) {
        SiteInfo siteInfoOld = new SiteInfo();
        for (SiteInfo i : siteInfoRepository.findAll()) {
            siteInfoOld = i;
        }
        siteInfoOld.setFooterText(siteInfo.getFooterText());
        siteInfoRepository.save(siteInfoOld);
    }

    @Override
    public void turnSliderOnOrOff(SiteInfo siteInfo, boolean isEnabled) {
        SiteInfo siteInfoOld = new SiteInfo();
        for (SiteInfo i : siteInfoRepository.findAll()) {
            siteInfoOld = i;
        }
        if(isEnabled){
            siteInfoOld.setSliderEnabled(true);
        } else {
            siteInfoOld.setSliderEnabled(false);
        }
        siteInfoRepository.save(siteInfoOld);
    }

    @Override
    public void uploadSlider(MultipartFile[] files, SiteInfo siteInfo, Slider slider) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadSliderDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }
        slider.setPhotoPath("/img/slider/"+fileNames);
        sliderRepository.save(slider);
    }

    @Override
    public void deleteSliderPic(Long id) {
        Slider slider = sliderRepository.getOne(id);
        sliderRepository.delete(slider);
    }

    @Override
    public void updateSlider(Long id, Slider slider) {
        Slider sliderOld = sliderRepository.getOne(id);
        if(!sliderOld.getTitle().equals(slider.getTitle()) && !slider.getTitle().equals("")){
            sliderOld.setTitle(slider.getTitle());
        }
        if(!sliderOld.getPhotoText().equals(slider.getPhotoText()) && !slider.getPhotoText().equals("")){
            sliderOld.setPhotoText(slider.getPhotoText());
        }
        sliderRepository.save(sliderOld);
    }

    @Override
    public void newPost(MultipartFile[] files, Post post) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadPostDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }
        post.setPhotoPath("/img/posts/"+fileNames);
        String nowDate = String.valueOf(java.time.LocalDate.now());
        post.setDateAndTime(nowDate);
        postRepository.save(post);
        Category postCategory = post.getCategory();
        int catPostsCunter = postCategory.getPostsNum();
        postCategory.setPostsNum(catPostsCunter+1);
        categoryRepository.save(postCategory);
    }

    @Override
    public void newCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void showCategoryPost(long id, Model model) {
        List categoryPosts = postRepository.showPostsByCategoryId(id);
        Collections.reverse(categoryPosts);
        model.addAttribute("categoryPosts", categoryPosts);
        model.addAttribute("categoryInfo", categoryRepository.getOne(id));
    }

    @Override
    public void editPost(Post post, long id) {
        Post oldPost = postRepository.getOne(id);
        oldPost.setText(post.getText());
        oldPost.setTitle(post.getTitle());
        if(post.getCategory() != oldPost.getCategory() && post.getCategory() != null){
            Category oldPostCategory = oldPost.getCategory();
            int nowPosts = oldPostCategory.getPostsNum();
            oldPostCategory.setPostsNum(nowPosts-1);
            categoryRepository.save(oldPostCategory);
            Category newPostCategory = post.getCategory();
            int newCatNoPosts = newPostCategory.getPostsNum();
            newPostCategory.setPostsNum(newCatNoPosts+1);
            categoryRepository.save(newPostCategory);
            oldPost.setCategory(newPostCategory);
        }
        postRepository.save(oldPost);
    }

    @Override
    public void removePost(Long id) {
        Post post = postRepository.getOne(id);
        Category category = post.getCategory();
        int categoryPostsCounter = category.getPostsNum();
        category.setPostsNum(categoryPostsCounter-1);
        categoryRepository.save(category);
        postRepository.deleteById(id);
    }

    @Override
    public void deleteAllPosts() {
        postRepository.deleteAll();
        for (Category category : categoryRepository.findAll()) {
            category.setPostsNum(0);
            categoryRepository.save(category);
        }
    }
}
