import com.treeNode.UserTypeEnum;
import com.treeNode.pojo.request.UserRequest;
import com.treeNode.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class UserNodeTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
        UserRequest userReq = new UserRequest();
        userReq.setUserName("test1");
        userReq.setUserType(UserTypeEnum.ADMIN.getCode());
        userService.saveUser(userReq);
    }
}
