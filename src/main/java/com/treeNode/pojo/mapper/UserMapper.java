package com.treeNode.pojo.mapper;

import com.treeNode.pojo._do.User;

public interface UserMapper {
    User selectByPrimaryKey(Integer bookId);

    int insert(User record);

    int updateByPrimaryKey(User record);

    int deleteByPrimaryKey(Integer id);
}
