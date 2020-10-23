package com.shirodemo.mapper;

import com.shirodemo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper{
    public User findUserByName(String name);
}
