package com.wisewintech.service;

import com.wisewintech.base.entity.UserBO;
import com.wisewintech.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserDAO userDAO;

    public List<UserBO> getUserBOList(){
        userDAO.addUserBO();
        return userDAO.getUserBOList();
    }
}
