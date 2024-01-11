package com.treeNode.service.impl;

import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo.mapper.TreeNodeMapper;
import com.treeNode.pojo.mapper.UserMapper;
import com.treeNode.pojo.mapper.UserTreeRelMapper;
import com.treeNode.pojo.request.NodeInfo;
import com.treeNode.pojo.request.UserNodeInfo;
import com.treeNode.service.UserNodeService;
import com.treeNode.util.GlobalConstants;
import javafx.css.Styleable;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Log
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
    public void addTree(UserNodeInfo userNodeReq) {
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
     * 删除树节点
     *
     * @param userNodeReq
     */
    @Override
    public void delTreeNode(UserNodeInfo userNodeReq) {

    }

    /**
     * 查询树节点
     *
     * @param userNodeReq
     */
    @Override
    public UserNodeInfo selectTreeNode(UserNodeInfo userNodeReq) {
        UserNodeInfo userNodeResp = new UserNodeInfo();
        userNodeResp.setTreeName(userNodeReq.getTreeName());
        NodeInfo nodeInfo = new NodeInfo();
        Map<Integer, List<NodeInfo>> nodeMap = new HashMap<>();
        TreeNode treeNode = new TreeNode();
        treeNode.setTreeName(userNodeReq.getTreeName());
        HashMap<Integer,NodeInfo> tempMap = new HashMap<>();
        List<TreeNode> treeNodeList = treeNodeMapper.selectBySelective(treeNode);
        List<TreeNode> root = treeNodeList.stream().filter(t->treeNode.getParentId()==null).collect(Collectors.toList());
        TreeNode rootnode = root.get(0);
        nodeInfo.setNodeId(rootnode.getId());
        nodeInfo.setNodeName(rootnode.getNodeName());
       if (!CollectionUtils.isEmpty(treeNodeList)) {
            treeNodeList.stream().forEach(node -> {
                NodeInfo nodeInfoTemp = new NodeInfo();
                nodeInfoTemp.setNodeId(node.getId());
                nodeInfoTemp.setNodeName(node.getNodeName());
                nodeInfoTemp.setParentId(node.getParentId());

                tempMap.put(node.getId(), nodeInfoTemp);
                List<NodeInfo> nodeList = null;
                if (null == nodeMap.get(node.getParentId())) {
                    nodeList = new ArrayList<>();
                } else {
                    nodeList = nodeMap.get(node.getParentId());
                }
                nodeList.add(nodeInfoTemp);
                if(node.getParentId()!=null) {
                    nodeMap.put(node.getParentId(), nodeList);
                }
            });
        }
        for(Map.Entry<Integer, List<NodeInfo>> uu : nodeMap.entrySet()) {
            NodeInfo n1 = tempMap.get(uu.getKey());
            n1.setSubNodeList(nodeMap.get(n1.getNodeId()));
        }
        nodeInfo.setSubNodeList(nodeMap.get(nodeInfo.getNodeId()));
        userNodeResp.setNodeInfo(nodeInfo);
        return userNodeResp;
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
