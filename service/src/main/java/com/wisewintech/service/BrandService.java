package com.wisewintech.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wisewintech.base.entity.bo.BrandBO;
import com.wisewintech.dao.BrandDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (BrandBO)表服务实现类
 *
 * @author makejava
 * @since 2019-12-31 10:04:58
 */
@Service("BrandService")
public class BrandService  {
    @Autowired
    private BrandDAO maBrandDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BrandBO queryById(Long id) {
        return this.maBrandDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    public  IPage<BrandBO> queryAllByLimit(int pageNo,int pageSize,BrandBO brandBO) {
        Page<BrandBO> page = new Page<BrandBO>(pageNo, pageSize);  // 查询第1页，每页返回5条
        IPage<BrandBO> iPage = maBrandDao.queryAllByLimit(page, brandBO);
        return   iPage;
    }

    /**
     * 新增数据
     *
     * @param maBrand 实例对象
     * @return 实例对象
     */
    public BrandBO insert(BrandBO maBrand) {
        maBrand.setCreateTime(new Date());
        this.maBrandDao.insert(maBrand);
        return maBrand;
    }

    /**
     * 修改数据
     *
     * @param maBrand 实例对象
     * @return 实例对象
     */
    public BrandBO update(BrandBO maBrand) {
        maBrand.setUpdateTime(new Date());
        this.maBrandDao.update(maBrand);
        return this.queryById(maBrand.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id) {
        return this.maBrandDao.deleteById(id) > 0;
    }

    /**
     * 查询所有状态正常的数据
     */
    public List<BrandBO> brandList() {
        return this.maBrandDao.brandList();
    }
}