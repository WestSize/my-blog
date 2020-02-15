package com.example.myblog.web.repositores;

import com.example.myblog.web.entities.SiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteInfoRepository extends JpaRepository<SiteInfo, String> {
}
