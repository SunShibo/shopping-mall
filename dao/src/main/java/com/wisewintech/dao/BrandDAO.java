package com.wisewintech.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wisewintech.base.entity.bo.BrandBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 品牌
 *
 * @author makejava
 * @since 2019-12-31 10:04:58
 */
@Component
public interface BrandDAO {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BrandBO queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    IPage<BrandBO> queryAllByLimit(Page<BrandBO> page, @Param("brandBO")BrandBO brandBO);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param BrandBO 实例对象
     * @return 对象列表
     */
    List<BrandBO> queryAll(BrandBO BrandBO);

    /**
     * 新增数据
     *
     * @param BrandBO 实例对象
     * @return 影响行数
     */
    int insert(BrandBO BrandBO);

    /**
     * 修改数据
     *
     * @param BrandBO 实例对象
     * @return 影响行数
     */
    int update(BrandBO BrandBO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 查询下拉列表
     * @return
     */
    List<BrandBO> brandList();

}