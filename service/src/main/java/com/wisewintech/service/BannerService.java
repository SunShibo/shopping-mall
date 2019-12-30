package com.wisewintech.service;

import com.wisewintech.base.entity.bo.BannerBO;
import com.wisewintech.dao.BannerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BannerService {
    @Autowired
    BannerDAO bannerDAO;

    public Integer addBannerBO(BannerBO bannerBO) {
        return bannerDAO.addBannerBO(bannerBO);
    }

    public Integer updBannerBO(BannerBO bannerBO) {
        return bannerDAO.updBannerBO(bannerBO);
    }

    public Integer delBannerBO(Integer id) {
        return bannerDAO.delBannerBO(id);
    }

    public Integer getBannerBOList() {
        return bannerDAO.getBannerBOList();
    }
}
