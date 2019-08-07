package com.bjpowernode.service;

import com.bjpowernode.model.User;

import java.util.List;

public interface UserService {
    Integer login(User user);

    Integer add(User user);

    List<User> findUserAll();

    User findUserByLoginName(String loginName);
}
