package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.model.User;
import com.bjpowernode.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
/**
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Integer login(User user) {
        User tempUser=userMapper.selectUserByLoginName(user.getLoginName());
        if (tempUser==null){
            return 1;
        }else if (!tempUser.getPassword().equals(user.getPassword())){
            return 2;
        }
        BeanUtils.copyProperties(tempUser, user);
        return 0;
    }

    @Override
    public Integer add(User user) {
        User tempUser = userMapper.selectUserByLoginName(user.getLoginName());
        System.out.println(user.getLoginName());
        //进入if表示用户名已经存在,拒绝用户的添加 返回1表示添加失败
        if (tempUser!=null){
            return 1;
        }
        userMapper.insert(user);
        return 0;
    }

    @Override
    public List<User> findUserAll() {
        return userMapper.selectUserAll();
    }

    @Override
    public User findUserByLoginName(String loginName) {

        return userMapper.selectUserByLoginName(loginName);
    }
}
