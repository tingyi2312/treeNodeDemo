package com.treeNode.pojo.mapper;

import com.treeNode.pojo._do.TreeNode;

/**
 * 树表实体类
 */

public interface TreeNodeMapper {

    TreeNode selectByPrimaryKey(Integer id);

    int insert(TreeNode treeNode);

    int updateByPrimaryKey(TreeNode treeNode);

    int deleteByPrimaryKey(Integer id);

}
