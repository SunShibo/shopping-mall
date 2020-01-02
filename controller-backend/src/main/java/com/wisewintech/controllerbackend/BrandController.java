package com.wisewintech.controllerbackend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wisewintech.base.config.constants.Constants;
import com.wisewintech.base.entity.bo.BrandBO;
import com.wisewintech.base.entity.dto.ResultDTO;
import com.wisewintech.base.entity.dto.ResultDTOBuilder;
import com.wisewintech.controllerbackend.base.BaseCotroller;
import com.wisewintech.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/brand")
public class BrandController extends BaseCotroller {
    static final Logger log = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @PostMapping("/addBrand")
    public ResultDTO addBrand(BrandBO brandBO ,HttpServletRequest request, HttpServletResponse response) {
        log.info("do addBrand....");
        if (!verifyParam(brandBO.getName())) {
            return ResultDTOBuilder.failure("00001");
        }
        brandService.insert(brandBO);
        return ResultDTOBuilder.success();
    }

    @PostMapping("/delBrand")
    public ResultDTO delBrand(BrandBO brandBO ,HttpServletRequest request, HttpServletResponse response) {
        log.info("do delBrand....");
        if (!verifyParam(brandBO.getId())) {
            return ResultDTOBuilder.failure("00001");
        }
        brandBO.setStatus(Constants.NO.getValue());
        brandService.update(brandBO);
        return ResultDTOBuilder.success();
    }

    @PostMapping("/updBrand")
    public ResultDTO updBrand(BrandBO brandBO ,HttpServletRequest request, HttpServletResponse response) {
        log.info("do updBrand....");
        if (!verifyParam(brandBO.getId(),brandBO.getName())) {
            return ResultDTOBuilder.failure("00001");
        }
        brandService.update(brandBO);
        return ResultDTOBuilder.success();
    }

    @PostMapping("/queryBrand")
    public ResultDTO queryBrand(BrandBO brandBO , Integer pageNo, Integer pageSize,HttpServletRequest request, HttpServletResponse response) {
        log.info("do queryBrand....");
        if (!verifyParam(pageNo,pageSize)) {
            return ResultDTOBuilder.failure("00001");
        }
        IPage<BrandBO> brandB = brandService.queryAllByLimit(pageNo, pageSize, brandBO);
        brandService.update(brandBO);
        return ResultDTOBuilder.success(brandB);
    }

    /**
     * 品牌下拉框
     * @param brandBO
     * @param pageNo
     * @param pageSize
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/brandList")
    public ResultDTO brandList(BrandBO brandBO , Integer pageNo, Integer pageSize,HttpServletRequest request, HttpServletResponse response) {
        List<BrandBO> brands = brandService.brandList();
        return ResultDTOBuilder.success(brands);
    }
}