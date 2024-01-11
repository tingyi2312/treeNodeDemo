package com.treeNode.service.impl;

import com.treeNode.controller.UserController;
import com.treeNode.enums.UserTypeEnum;
import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo._do.User;
import com.treeNode.pojo._do.UserTreeRel;
import com.treeNode.pojo.mapper.TreeNodeMapper;
import com.treeNode.pojo.mapper.UserMapper;
import com.treeNode.pojo.mapper.UserTreeRelMapper;
import com.treeNode.pojo.request.NodeInfo;
import com.treeNode.pojo.request.UserNodeInfo;
import com.treeNode.pojo.request.UserRelNode;
import com.treeNode.service.UserNodeService;
import com.treeNode.service.UserService;
import com.treeNode.util.GlobalConstants;
import javafx.css.Styleable;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Log
public class UserNodeServiceImpl implements UserNodeService {

    public static final Logger logger = LoggerFactory.getLogger(UserNodeServiceImpl.class);

    @Autowired
    private TreeNodeMapper treeNodeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTreeRelMapper userTreeRelMapper;

    @Autowired
    UserService userService;

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
     * 查询树
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
                if(node.getParentId() != null) {
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
     * 增加树节点
     *
     * @param treeNode
     */
    @Override
    public void addTreeNode(TreeNode treeNode) {
        treeNode.setActive(GlobalConstants.ACTIVE_YES);
        treeNode.setCreateTime(new Date());
        treeNode.setUpdateTime(new Date());
        treeNodeMapper.insert(treeNode);
    }

    /**
     * 删除树节点
     *
     * @param treeNode
     */
    @Override
    public void delTreeNode(TreeNode treeNode) {
        treeNode.setActive(GlobalConstants.ACTIVE_NO);
        treeNode.setUpdateTime(new Date());
        treeNodeMapper.updateBySelective(treeNode);
    }

    private Boolean hasPrivate(String userName) {
        User user = userService.getUserInfoByName(userName);
        if (null == user || UserTypeEnum.NORMAL.getCode().equals(user.getUserType())) {
            return false;
        }
        return true;
    }


    /**
     * 用户与树的节点的增加操作
     * @param userNodeReq
     */
    @Override
    public Boolean addUserTreeRel(UserNodeInfo userNodeReq) {
        User user = userService.getUserInfoByName(userNodeReq.getUserName());
        if (null == user || UserTypeEnum.NORMAL.getCode().equals(user.getUserType())) {
            logger.error("非管理员，没有操作权限");
            return false;
        }
        if (null == userNodeReq.getNodeInfo()) {
            logger.error("节点信息为空");
            return false;
        }
        try {
            setUserNodeInfo(user.getId(), userNodeReq.getNodeInfo());
        } catch (Exception e) {
            logger.error("用户与树的节点的增加操作失败:", e);
            return false;
        }
        return true;
    }

    private void setUserNodeInfo(Integer userId, NodeInfo nodeInfo) {
        UserTreeRel userTreeRel = new UserTreeRel();
        userTreeRel.setUserId(userId);
        userTreeRel.setNodeId(nodeInfo.getNodeId());
        userTreeRel.setActive(GlobalConstants.ACTIVE_YES);
        userTreeRel.setCreateTime(new Date());
        userTreeRel.setUpdateTime(new Date());
        userTreeRelMapper.insert(userTreeRel);
        if (!CollectionUtils.isEmpty(nodeInfo.getSubNodeList())) {
            nodeInfo.getSubNodeList().forEach(node -> {
                setUserNodeInfo(userId, node);
            });
        }
    }

    /**
     * 用户与树的节点的删除操作
     *
     * @param id
     */
    @Override
    public Boolean delUserTreeNode(String userName, Integer id) {
        User user = userService.getUserInfoByName(userName);
        if (null == user || UserTypeEnum.NORMAL.getCode().equals(user.getUserType())) {
            logger.error("非管理员，没有操作权限");
            return false;
        }
        UserTreeRel userTreeRel = new UserTreeRel();
        userTreeRel.setId(id);
        userTreeRel.setActive(GlobalConstants.ACTIVE_NO);
        userTreeRel.setUpdateTime(new Date());
        int num = userTreeRelMapper.updateByPrimaryKey(userTreeRel);
        return num >0 ? true : false;
    }

    /**
     * 用户与树的节点的查询操作
     *
     * @param userNodeReq
     */
    @Override
    public List<UserRelNode> selectUserTreeNode(UserNodeInfo userNodeReq) {
        User user = userService.getUserInfoByName(userNodeReq.getUserName());
        if (null == user) {
            logger.error("用户信息为空");
            return null;
        }

        UserTreeRel userTreeRel = new UserTreeRel();
        userTreeRel.setUserId(user.getId());
        List<UserRelNode> userTreeRelList = userTreeRelMapper.selectUserRelNode(userTreeRel);
        return userTreeRelList;
    }
}
