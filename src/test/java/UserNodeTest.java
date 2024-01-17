import com.alibaba.fastjson.JSONObject;
import com.treeNode.BootStrapApplication;
import com.treeNode.enums.UserTypeEnum;
import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo.request.NodeInfo;
import com.treeNode.pojo.request.UserNodeInfo;
import com.treeNode.pojo.request.UserRelNode;
import com.treeNode.pojo.request.UserRequest;
import com.treeNode.service.UserNodeService;
import com.treeNode.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrapApplication.class)
public class UserNodeTest {
    public static final Logger logger = LoggerFactory.getLogger(UserNodeTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserNodeService userNodeService;

    /**
     * 保存用户信息
     */
    @Test
    public void testSaveUser() {
        UserRequest userReq = new UserRequest();
        userReq.setUserName("test1");
        userReq.setUserType(UserTypeEnum.ADMIN.getCode());
        userService.saveUser(userReq);
    }

    /**
     * 保存N叉树
     */
    @Test
    public void testAddTree() {
        UserNodeInfo userNodeReq = new UserNodeInfo();
        userNodeReq.setTreeName("tree1");
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setNodeName("root");
        NodeInfo nodeInfoA1 = new NodeInfo();
        nodeInfoA1.setNodeName("a1");
        nodeInfo.setSubNodeList(Arrays.asList(nodeInfoA1));

        NodeInfo nodeInfoB1 = new NodeInfo();
        nodeInfoB1.setNodeName("b1");
        NodeInfo nodeInfoB2 = new NodeInfo();
        nodeInfoB2.setNodeName("b2");
        nodeInfoA1.setSubNodeList(Arrays.asList(nodeInfoB1, nodeInfoB2));

        NodeInfo nodeInfoC1 = new NodeInfo();
        nodeInfoC1.setNodeName("c1");
        NodeInfo nodeInfoC2 = new NodeInfo();
        nodeInfoC2.setNodeName("c2");
        nodeInfoB2.setSubNodeList(Arrays.asList(nodeInfoC1, nodeInfoC2));
        userNodeReq.setNodeInfo(nodeInfo);

        logger.info("保存N叉树request:{}", JSONObject.toJSONString(userNodeReq));
        Boolean result = userNodeService.addTree(userNodeReq);
        logger.info("保存N叉树result:{}", result);
        Assert.assertTrue(result);
    }


    /**
     * 查询树
     */
    @Test
    public void testQueryTree() {
        UserNodeInfo userNodeReq = new UserNodeInfo();
        userNodeReq.setTreeName("tree1");
        logger.info("查询树request:{}", JSONObject.toJSONString(userNodeReq));
        UserNodeInfo userNodeInfo = userNodeService.selectTreeNode(userNodeReq);
        logger.info("查询树result:{}",  JSONObject.toJSONString(userNodeInfo));
    }

    /**
     * 增加树节点
     */
    @Test
    public void testAddTreeNode() {
        TreeNode treeNode = new TreeNode();
        treeNode.setTreeName("tree1");
        treeNode.setNodeName("newNode");
        treeNode.setParentId(2);
        logger.info("增加树节点request:{}", JSONObject.toJSONString(treeNode));
        Boolean result = userNodeService.addTreeNode(treeNode);
        logger.info("增加树节点result:{}", JSONObject.toJSONString(treeNode));
        Assert.assertTrue(result);
    }

    /**
     * 删除树节点
     */
    @Test
    public void testDelTreeNode() {
        TreeNode treeNode = new TreeNode();
        treeNode.setTreeName("tree1");
        treeNode.setNodeName("newNode");
        treeNode.setParentId(2);
        logger.info("删除树节点request:{}", JSONObject.toJSONString(treeNode));
        Boolean result = userNodeService.delTreeNode(treeNode);
        logger.info("删除树节点result:{}", result);
        Assert.assertTrue(result);
    }

    /**
     * 用户与树的节点的增加操作
     */
    @Test
    public void addUserTreeRel() {
        UserNodeInfo userNodeReq = new UserNodeInfo();
        userNodeReq.setUserName("test1");
        userNodeReq.setTreeName("tree1");

        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setNodeName("root");
        nodeInfo.setNodeId(1);
        NodeInfo nodeInfoA1 = new NodeInfo();
        nodeInfoA1.setNodeName("a1");
        nodeInfoA1.setNodeId(2);
        nodeInfo.setSubNodeList(Arrays.asList(nodeInfoA1));

        NodeInfo nodeInfoB1 = new NodeInfo();
        nodeInfoB1.setNodeName("b1");
        nodeInfoB1.setNodeId(3);
        NodeInfo nodeInfoB2 = new NodeInfo();
        nodeInfoB2.setNodeName("b2");
        nodeInfoB2.setNodeId(54);
        nodeInfoA1.setSubNodeList(Arrays.asList(nodeInfoB1, nodeInfoB2));
        nodeInfo.setSubNodeList(Arrays.asList(nodeInfoA1));
        userNodeReq.setNodeInfo(nodeInfo);
        logger.info("增加用户与树的节点request:{}", JSONObject.toJSONString(userNodeReq));
        Boolean result = userNodeService.addUserTreeRel(userNodeReq);
        logger.info("增加用户与树的节点result:{}", result);
        Assert.assertTrue(result);
    }

    /**
     * 用户与树的节点的删除操作
     */
    @Test
    public void delUserTreeNode() {
        Boolean result = userNodeService.delUserTreeNode("test1", "tree1", 13);
        logger.info("删除用户与树的节点request:{}", result);
        Assert.assertTrue(result);
    }

    /**
     * 用户与树的节点的查询操作
     */
    @Test
    public void selectUserTreeNode() {
        UserNodeInfo userNodeReq = new UserNodeInfo();
        userNodeReq.setUserName("test1");
        userNodeReq.setTreeName("tree1");
        logger.info("查询用户与树的节点的request:{}", JSONObject.toJSONString(userNodeReq));
        List<NodeInfo> nodeList = userNodeService.selectUserTreeNode(userNodeReq);
        logger.info("查询用户与树的节点request:{}", JSONObject.toJSONString(nodeList));
    }

}

