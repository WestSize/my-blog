package com.example.myblog.web.services.AdminService;

import com.example.myblog.web.entities.SiteInfo;
import com.example.myblog.web.repositores.SiteInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private SiteInfoRepository siteInfoRepository;

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
}
