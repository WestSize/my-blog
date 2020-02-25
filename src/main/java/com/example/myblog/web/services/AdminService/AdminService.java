package com.example.myblog.web.services.AdminService;

import com.example.myblog.web.entities.Category;
import com.example.myblog.web.entities.Post;
import com.example.myblog.web.entities.SiteInfo;
import com.example.myblog.web.entities.Slider;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdminService {
    void getSiteInfo(Model model);
    void updateBlogName(SiteInfo siteInfo);
    void updateBlogAuthor(SiteInfo siteInfo);
    void updateBlogFooter(SiteInfo siteInfo);
    void turnSliderOnOrOff(SiteInfo siteInfo, boolean isEnabled);
    void uploadSlider(MultipartFile[] files, SiteInfo siteInfo, Slider slider) throws IOException;
    void deleteSliderPic(Long id);
    void updateSlider(Long id, Slider slider);
    void newPost(MultipartFile[] files, Post post) throws IOException;
    void newCategory(Category category);
    void showCategoryPost(long id, Model model);
    void editPost(Post post, long id);
    void removePost(Long id);
    void deleteAllPosts();
}
