package com.example.myblog.web.entities;

import javax.persistence.*;

@Entity
public class SiteInfo {
    @Id
    @Column(unique = true)
    private int blogId;
    @Column
    private String blogName;
    @Column
    private String authorName;
    @Lob
    @Column(length=512)
    private String footerText;
    @Column
    private String cssPath;
    @Column
    private boolean isSliderEnabled;
    @Column
    private boolean isSearchMenuEnabled;
    @Column
    private  boolean isCategoriesMenuEnabled;

    public SiteInfo() {
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    public String getCssPath() {
        return cssPath;
    }

    public void setCssPath(String cssPath) {
        this.cssPath = cssPath;
    }

    public boolean isSliderEnabled() {
        return isSliderEnabled;
    }

    public void setSliderEnabled(boolean sliderEnabled) {
        isSliderEnabled = sliderEnabled;
    }

    public boolean isSearchMenuEnabled() {
        return isSearchMenuEnabled;
    }

    public void setSearchMenuEnabled(boolean searchMenuEnabled) {
        isSearchMenuEnabled = searchMenuEnabled;
    }

    public boolean isCategoriesMenuEnabled() {
        return isCategoriesMenuEnabled;
    }

    public void setCategoriesMenuEnabled(boolean categoriesMenuEnabled) {
        isCategoriesMenuEnabled = categoriesMenuEnabled;
    }
}
