package com.shirodemo.service.impl;

import com.shirodemo.bean.User;
import com.shirodemo.mapper.UserMapper;
import com.shirodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }
}
