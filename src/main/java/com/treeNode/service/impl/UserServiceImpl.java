package com.treeNode.service.impl;

import com.treeNode.pojo._do.User;
import com.treeNode.pojo.mapper.UserMapper;
import com.treeNode.pojo.request.UserNodeRequest;
import com.treeNode.pojo.request.UserRequest;
import com.treeNode.service.UserService;
import com.treeNode.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(UserRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserType(request.getUserType());
        user.setActive(GlobalConstants.ACTIVE_YES);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
    }
}
