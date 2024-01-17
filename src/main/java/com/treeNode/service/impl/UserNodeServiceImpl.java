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
     * 添加一棵树
     *
     * @param userNodeReq
     */
    @Override
    public Boolean addTree(UserNodeInfo userNodeReq) {
        try {
            if (null != userNodeReq.getNodeInfo()) {
                setNodeInfo(userNodeReq.getTreeName(),null, userNodeReq.getNodeInfo());
            }
            return true;
        } catch (Exception e) {
            logger.error("添加树异常", e );
        }
        return false;
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
    public Boolean addTreeNode(TreeNode treeNode) {
        treeNode.setActive(GlobalConstants.ACTIVE_YES);
        treeNode.setCreateTime(new Date());
        treeNode.setUpdateTime(new Date());
        int num = treeNodeMapper.insert(treeNode);
        return num > 0 ? true : false;
    }

    /**
     * 删除树节点
     *
     * @param treeNode
     */
    @Override
    public Boolean delTreeNode(TreeNode treeNode) {
        treeNode.setActive(GlobalConstants.ACTIVE_NO);
        treeNode.setUpdateTime(new Date());
        int num = treeNodeMapper.updateBySelective(treeNode);
        return num > 0 ? true : false;
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
    public Boolean delUserTreeNode(String userName, String treeName, Integer id) {
        User user = userService.getUserInfoByName(userName);
        if (null == user || UserTypeEnum.NORMAL.getCode().equals(user.getUserType())) {
            logger.error("非管理员，没有操作权限");
            return false;
        }

        //查询用户关联的所有树的节点
        UserRelNode userRelNode = new UserRelNode();
        userRelNode.setUserId(user.getId());
        userRelNode.setTreeName(treeName);
        List<UserRelNode> userTreeRelList = userTreeRelMapper.selectUserRelNode(userRelNode);
        if (!CollectionUtils.isEmpty(userTreeRelList)) {
            //获取要删除的节点
            List<UserRelNode> nodeList = userTreeRelList.stream().filter(item -> id == item.getId()).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(nodeList)) {
                delUserTreeNode(nodeList.get(0), userTreeRelList);
            } else {
                logger.error("当前节点查询为空");
            }
        }

        return true;
    }

    private Boolean delUserTreeNode(UserRelNode userRelNode, List<UserRelNode> userTreeRelList) {
        //删除当前节点
        UserTreeRel userTreeRel = new UserTreeRel();
        userTreeRel.setId(userRelNode.getId());
        userTreeRel.setActive(GlobalConstants.ACTIVE_NO);
        userTreeRel.setUpdateTime(new Date());
        userTreeRelMapper.updateByPrimaryKey(userTreeRel);

        //删除子节点
        List<UserRelNode> subNodeList = userTreeRelList.stream().filter(item -> userRelNode.getNodeId() == item.getParentId()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(subNodeList)) {
            subNodeList.stream().forEach(sub -> {
                delUserTreeNode(sub, userTreeRelList);
            });
        }
        return true;
    }

    /**
     * 用户与树的节点的查询操作
     * @param userNodeReq
     */
    @Override
    public List<NodeInfo> selectUserTreeNode(UserNodeInfo userNodeReq) {
        User user = userService.getUserInfoByName(userNodeReq.getUserName());
        if (null == user) {
            logger.error("用户信息为空");
            return null;
        }

        UserRelNode userRelNode = new UserRelNode();
        userRelNode.setUserId(user.getId());
        userRelNode.setTreeName(userNodeReq.getTreeName());
        List<UserRelNode> userTreeRelList = userTreeRelMapper.selectUserRelNode(userRelNode);
        return userTreeRelList.stream()
                .filter(item -> null== item.getParentId())
                .map(item -> {
                    NodeInfo nodeInfo = new NodeInfo();
                    nodeInfo.setNodeId(item.getNodeId());
                    nodeInfo.setNodeName(item.getNodeName());
                    nodeInfo.setParentId(item.getParentId());
                    nodeInfo.setSubNodeList(getChild(item.getNodeId(), userTreeRelList));
                    return nodeInfo;
                })
                //.sorted(Comparator.comparingInt(menu -> (menu.getSortOrder() == null ? 0 : menu.getSortOrder())))
                .collect(Collectors.toList());
    }

    private List<NodeInfo> getChild(Integer nodeId, List<UserRelNode> userTreeRelList) {
        return userTreeRelList.stream()
                .filter(item -> Objects.equals(item.getParentId(), nodeId))
                .map(item -> {
                    NodeInfo nodeInfo = new NodeInfo();
                    nodeInfo.setNodeId(item.getNodeId());
                    nodeInfo.setNodeName(item.getNodeName());
                    nodeInfo.setParentId(item.getParentId());
                    nodeInfo.setSubNodeList(getChild(item.getNodeId(), userTreeRelList));
                    return nodeInfo;
                 })
//              .sorted(Comparator.comparingInt(menu -> (menu.getSortOrder() == null ? 0 : menu.getSortOrder())))
                .collect(Collectors.toList());
    }
}
