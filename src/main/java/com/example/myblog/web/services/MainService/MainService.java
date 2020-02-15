package com.example.myblog.web.services.MainService;

import org.springframework.ui.Model;

public interface MainService {
    void loadSiteSettings();
    void getSiteInfo(Model model);
}
