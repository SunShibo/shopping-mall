package com.wisewintech.dao;

import com.wisewintech.base.entity.UserBO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDAO {
    List<UserBO> getUserBOList();
    Integer addUserBO();
}
