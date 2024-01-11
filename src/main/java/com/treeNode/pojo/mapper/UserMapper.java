package com.treeNode.pojo.mapper;

import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo._do.User;

import java.util.List;

public interface UserMapper {
    User selectByPrimaryKey(Integer id);

    List<User> selectBySelective(User user);

    int insert(User record);

   int updateByPrimaryKey(User record);

//    int deleteByPrimaryKey(Integer id);
}
