package com.wisewintech.dao;

import com.wisewintech.base.entity.bo.entity.UserBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户表(MaUser)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-30 14:15:35
 */
@Component
public interface UserDAO {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserBO queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserBO> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userBO 实例对象
     * @return 对象列表
     */
    List<UserBO> queryAll(UserBO userBO);

    /**
     * 新增数据
     *
     * @param userBO 实例对象
     * @return 影响行数
     */
    int insert(UserBO userBO);

    /**
     * 修改数据
     *
     * @param userBO 实例对象
     * @return 影响行数
     */
    int update(UserBO userBO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    UserBO queryByPhone(@Param("mobile") String phone);

    UserBO queryByQQ(@Param("openId") String openId);

    UserBO queryByWeinxin(@Param("openId") String openId);

    void removeQQ(@Param("id") Long id);
    void removeWeixin(@Param("id")Long id);

}