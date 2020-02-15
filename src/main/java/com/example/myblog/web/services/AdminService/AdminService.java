package com.example.myblog.web.services.AdminService;

import com.example.myblog.web.entities.SiteInfo;
import org.springframework.ui.Model;

public interface AdminService {
    void getSiteInfo(Model model);
    void updateBlogName(SiteInfo siteInfo);
    void updateBlogAuthor(SiteInfo siteInfo);
    void updateBlogFooter(SiteInfo siteInfo);
    void turnSliderOnOrOff(SiteInfo siteInfo, boolean isEnabled);
}
