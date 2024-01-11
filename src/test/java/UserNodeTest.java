import com.treeNode.BootStrapApplication;
import com.treeNode.UserTypeEnum;
import com.treeNode.pojo._do.TreeNode;
import com.treeNode.pojo.request.NodeInfo;
import com.treeNode.pojo.request.UserNodeInfo;
import com.treeNode.pojo.request.UserRequest;
import com.treeNode.service.UserNodeService;
import com.treeNode.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrapApplication.class)
public class UserNodeTest {
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

        userNodeService.addTree(userNodeReq);
    }


    /**
     * 查询树
     */
    @Test
    public void testQueryTree() {
        UserNodeInfo userNodeReq = new UserNodeInfo();
        userNodeReq.setTreeName("tree1");
        userNodeService.selectTreeNode(userNodeReq);
    }

    /**
     * 增加树节点
     */
    @Test
    public void testAddTreeNode() {
        TreeNode treeNode  = new TreeNode();
        treeNode.setTreeName("tree1");
        treeNode.setNodeName("newNode");
        treeNode.setParentId(2);
        userNodeService.addTreeNode(treeNode);
    }

    /**
     * 删除树节点
     */
    @Test
    public void testDelTreeNode() {
        TreeNode treeNode  = new TreeNode();
        treeNode.setTreeName("tree1");
        treeNode.setNodeName("newNode");
        treeNode.setParentId(2);
        userNodeService.delTreeNode(treeNode);
    }
}
