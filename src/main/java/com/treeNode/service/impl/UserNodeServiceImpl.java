package com.treeNode.service.impl;

import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo._do.User;
import com.treeNode.pojo.mapper.TreeNodeMapper;
import com.treeNode.pojo.mapper.UserMapper;
import com.treeNode.pojo.mapper.UserTreeRelMapper;
import com.treeNode.pojo.request.NodeInfo;
import com.treeNode.pojo.request.UserNodeRequest;
import com.treeNode.service.UserNodeService;
import com.treeNode.service.UserService;
import com.treeNode.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserNodeServiceImpl implements UserNodeService {
    @Autowired
    private TreeNodeMapper treeNodeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTreeRelMapper userTreeRelMapper;

    /**
     * 添加一棵树(只有管理员允许添加树)
     *
     * @param userNodeReq
     */
    @Override
    public void addTree(UserNodeRequest userNodeReq) {
        if (null != userNodeReq.getNodeInfo()) {
            setNodeInfo(userNodeReq.getTreeName(),null, userNodeReq.getNodeInfo());
        }
    }

    private int setNodeInfo(String treeName, Integer parentId, NodeInfo nodeInfo) {
        TreeNode treeNode = new TreeNode();
        treeNode.setTreeName(treeName);
        treeNode.setNodeName(nodeInfo.getNodeName());
        treeNode.setParentId(parentId);
        treeNode.setActive(GlobalConstants.ACTIVE_YES);
        treeNode.setCreateTime(new Date());
        treeNode.setUpdateTime(new Date());
        treeNodeMapper.insert(treeNode);
        if (!CollectionUtils.isEmpty(nodeInfo.getSubNodeList())) {
            nodeInfo.getSubNodeList().forEach(node -> {
                setNodeInfo(treeName,treeNode.getId(), node);
            });
        }
        return treeNode.getId();
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
