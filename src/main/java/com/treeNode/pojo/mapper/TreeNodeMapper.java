package com.treeNode.pojo.mapper;

import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo._do.User;

import java.util.List;

/**
 * 树表实体类
 */

public interface TreeNodeMapper {

    TreeNode selectByPrimaryKey(Integer id);

    List<TreeNode> selectBySelective(TreeNode treeNode);

    int insert(TreeNode treeNode);

    int updateByPrimaryKey(TreeNode treeNode);

    int updateBySelective(TreeNode treeNode);

    int deleteByPrimaryKey(Integer id);

}
