package com.treeNode.service.impl;

import com.treeNode.pojo._do.User;
import com.treeNode.pojo.mapper.UserMapper;
import com.treeNode.pojo.request.NodeInfo;
import com.treeNode.pojo.request.UserNodeRequest;
import com.treeNode.service.UserNodeService;
import com.treeNode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNodeServiceImpl implements UserNodeService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 添加一棵树(只有管理员允许添加树)
     *
     * @param NodeInfo
     */
    @Override
    public void addTree(NodeInfo NodeInfo) {

    }

    /**
     * 增加树节点
     *
     * @param NodeInfo
     */
    @Override
    public void addTreeNode(NodeInfo NodeInfo) {

    }

    /**
     * 删除树节点
     *
     * @param NodeInfo
     */
    @Override
    public void delTreeNode(NodeInfo NodeInfo) {

    }

    /**
     * 查询树节点
     *
     * @param NodeInfo
     */
    @Override
    public NodeInfo selectTreeNode(NodeInfo NodeInfo) {
        return null;
    }

    /**
     * 用户与树的节点的增加操作
     *
     * @param NodeInfo
     */
    @Override
    public void addUserTreeNode(NodeInfo NodeInfo) {

    }

    /**
     * 用户与树的节点的删除操作
     *
     * @param NodeInfo
     */
    @Override
    public void delUserTreeNode(NodeInfo NodeInfo) {

    }

    /**
     * 用户与树的节点的查询操作
     *
     * @param NodeInfo
     */
    @Override
    public NodeInfo selectUserTreeNode(NodeInfo NodeInfo) {
        return null;
    }
}
