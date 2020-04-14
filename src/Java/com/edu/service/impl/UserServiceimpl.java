package com.edu.service.impl;

import com.edu.dao.UserDao;
import com.edu.dao.impl.UserDaoimpl;
import com.edu.domain.User;

public class UserServiceimpl implements com.edu.service.UserService {

    @Override
    public boolean login(User user) {
         //调用DAO方法查询用户是否存在
        UserDao userDao = new UserDaoimpl();
        return userDao.login(user);
    }
}
