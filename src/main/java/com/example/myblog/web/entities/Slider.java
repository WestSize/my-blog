package com.example.myblog.web.entities;

import javax.persistence.*;

@Entity
public class Slider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String photoPath;
    @Column
    private String title;
    @Column
    private String photoText;

    public Slider() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoText() {
        return photoText;
    }

    public void setPhotoText(String photoText) {
        this.photoText = photoText;
    }
}
