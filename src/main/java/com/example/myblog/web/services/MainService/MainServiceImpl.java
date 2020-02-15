package com.example.myblog.web.services.MainService;

import com.example.myblog.web.entities.SiteInfo;
import com.example.myblog.web.repositores.SiteInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Random;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private SiteInfoRepository siteInfoRepository;

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
            siteInfoRepository.save(siteInfo);
        }
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
        model.addAttribute("sliderStatus", siteInfo.isSliderEnabled());
    }
}
