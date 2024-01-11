package com.treeNode.pojo.mapper;

import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo._do.UserTreeRel;
import com.treeNode.pojo.request.UserRelNode;

import java.util.List;

public interface UserTreeRelMapper {

    UserTreeRel selectByPrimaryKey(Integer id);

    List<UserRelNode> selectUserRelNode(UserTreeRel record);

    int insert(UserTreeRel record);

    int updateByPrimaryKey(UserTreeRel record);

    int updateBySelective(UserTreeRel record);

    int deleteByPrimaryKey(Integer id);
}
