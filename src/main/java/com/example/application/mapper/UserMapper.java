package com.example.application.mapper;

import com.example.application.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author:happys
 * @Date: 2019/7/19
 * @Time: 15:20
 */
@Repository
public interface UserMapper {

    User Sel(int id);
    User login(String userName,String passWord);
    int register(User user);
}