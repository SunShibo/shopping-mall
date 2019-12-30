package com.wisewintech.dao;

import com.wisewintech.base.entity.bo.BannerBO;
import org.springframework.stereotype.Component;

@Component
public interface BannerDAO {
    Integer addBannerBO(BannerBO bannerBO);
    Integer updBannerBO(BannerBO bannerBO);
    Integer delBannerBO(Integer id);
    Integer getBannerBOList();
}
