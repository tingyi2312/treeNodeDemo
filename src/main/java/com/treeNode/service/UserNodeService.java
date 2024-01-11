package com.treeNode.service;

import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo.request.NodeInfo;
import com.treeNode.pojo.request.UserNodeInfo;
import com.treeNode.pojo.request.UserRelNode;

import java.util.List;

public interface UserNodeService {
/*    a.保存用户(区分管理员和普通用户)
    b.保存N叉树(任意棵)
    c.用户与N叉树的节点实现多对多的关联

    a.添加一棵树(只有管理员允许添加树)
    b.对树的节点的增/删/查(管理员可以增/删/查，普通用户可以查)
    c.用户与树的节点的增/删/查(管理员可以增/删/查，普通用户可以查)*/

    /**
     * 添加一棵树(只有管理员允许添加树)
     * @param userNodeRequest
     */
    void addTree(UserNodeInfo userNodeRequest);

    /**
     * 增加树节点
     * @param treeNode
     */
    void addTreeNode(TreeNode treeNode);

    /**
     * 删除树节点
     * @param treeNode
     */
    void delTreeNode(TreeNode treeNode);

    /**
     * 查询树节点
     * @param userNodeInfo
     */
    UserNodeInfo selectTreeNode(UserNodeInfo userNodeInfo);

    /**
     * 用户与树的节点的增加操作
     * @param userNodeReq
     */
    Boolean addUserTreeRel(UserNodeInfo userNodeReq);

    /**
     * 用户与树的节点的删除操作
     * @param userName
     * @param id
     */
    Boolean delUserTreeNode(String userName, Integer id);

    /**
     * 用户与树的节点的查询操作
     * @param userNodeReq
     */
    List<UserRelNode> selectUserTreeNode(UserNodeInfo userNodeReq);

}
