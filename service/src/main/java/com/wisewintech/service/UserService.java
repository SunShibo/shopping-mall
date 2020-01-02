package com.wisewintech.service;

import com.wisewintech.base.config.constants.Constants;
import com.wisewintech.base.entity.bo.UserBO;
import com.wisewintech.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 用户表(MaUser)表服务实现类
 *
 * @author makejava
 * @since 2019-12-30 14:15:36
 */
@Service("maUserService")
public class UserService {
    @Autowired
    private UserDAO maUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public UserBO queryById(Long id) {
        return this.maUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public List<UserBO> queryAllByLimit(int offset, int limit) {
        return this.maUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userBO 实例对象
     * @return 实例对象
     */
    public UserBO insert(UserBO userBO) {
        this.maUserDao.insert(userBO);
        return userBO;
    }

    /**
     * 修改数据
     *
     * @param userBO 实例对象
     * @return 实例对象
     */
    public UserBO update(UserBO userBO) {
        this.maUserDao.update(userBO);
        return this.queryById(userBO.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id) {
        return this.maUserDao.deleteById(id) > 0;
    }

    /**
     * 通过手机号找查
     * @param phone
     * @return
     */
    public UserBO queryByPhone(String phone) {
        return this.maUserDao.queryByPhone(phone);
    }

    public String queryByOpenid(String openid, String status) {
        UserBO userBO = null;
        //QQ登录
        if(status.equals(Constants.QQ.getValue())){
            userBO = this.maUserDao.queryByQQ(openid);
        }else if(status.equals(Constants.WEIXIN.getValue())){
            userBO = this.maUserDao.queryByWeinxin(openid);
        }
        return userBO==null?null:userBO.getMobile();
    }

    public void removeOpenId(Long id, String status) {
        if(status.equals(Constants.QQ.getValue())){
            this.maUserDao.removeQQ(id);
        }else if(status.equals(Constants.WEIXIN.getValue())){
            this.maUserDao.removeWeixin(id);
        }
    }
}