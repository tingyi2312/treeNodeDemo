package com.treeNode.service;

import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo.request.NodeInfo;
import com.treeNode.pojo.request.UserNodeInfo;

import java.util.List;

public interface UserNodeService {

    /**
     * 添加一棵树(只有管理员允许添加树)
     * @param userNodeRequest
     */
    Boolean addTree(UserNodeInfo userNodeRequest);

    /**
     * 增加树节点
     * @param treeNode
     */
    Boolean addTreeNode(TreeNode treeNode);

    /**
     * 删除树节点
     * @param treeNode
     */
    Boolean delTreeNode(TreeNode treeNode);

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
    Boolean delUserTreeNode(String userName, String treeName, Integer id);

    /**
     * 用户与树的节点的查询操作
     * @param userNodeReq
     */
    List<NodeInfo> selectUserTreeNode(UserNodeInfo userNodeReq);

}
