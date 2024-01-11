package com.treeNode.service;

import com.treeNode.pojo.request.NodeInfo;
import com.treeNode.pojo.request.UserNodeInfo;

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
     * @param NodeInfo
     */
    void addTreeNode(NodeInfo NodeInfo);

    /**
     * 删除树节点
     * @param NodeInfo
     */
    void delTreeNode(NodeInfo NodeInfo);

    /**
     * 查询树节点
     * @param NodeInfo
     */
    NodeInfo selectTreeNode(NodeInfo NodeInfo);

    /**
     * 用户与树的节点的增加操作
     * @param NodeInfo
     */
    void addUserTreeNode(NodeInfo NodeInfo);

    /**
     * 用户与树的节点的删除操作
     * @param NodeInfo
     */
    void delUserTreeNode(NodeInfo NodeInfo);

    /**
     * 用户与树的节点的查询操作
     * @param NodeInfo
     */
    NodeInfo selectUserTreeNode(NodeInfo NodeInfo);

    void delTreeNode(UserNodeInfo userNodeReq);

    UserNodeInfo selectTreeNode(UserNodeInfo userNodeReq);

}
