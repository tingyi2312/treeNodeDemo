package com.treeNode.pojo.mapper;

import com.treeNode.pojo._do.UserTreeRel;

public interface UserTreeRelMapper {

    UserTreeRel selectByPrimaryKey(Integer id);

    int insert(UserTreeRel record);

    int updateByPrimaryKey(UserTreeRel record);

    int deleteByPrimaryKey(Integer id);
}
