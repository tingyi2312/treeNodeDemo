package com.treeNode.service;

import com.treeNode.pojo._do.User;
import com.treeNode.pojo.request.UserRequest;


public interface UserService {
    void saveUser(UserRequest request);

    User getUserInfoByName(String userName);
}
