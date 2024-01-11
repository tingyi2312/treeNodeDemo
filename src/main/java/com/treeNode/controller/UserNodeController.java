package com.treeNode.controller;

import com.treeNode.pojo.Response.UserNodeResp;
import com.treeNode.pojo.request.UserNodeInfo;
import com.treeNode.service.UserNodeService;
import com.treeNode.service.UserService;
import com.treeNode.util.response.BaseRsp;
import com.treeNode.util.result.JsonResultBuilder;
import com.treeNode.util.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userNode")
public class UserNodeController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserNodeService userNodeService;

    /**
     * 根据id查询
     * @param bookVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUserTreeRel", method = RequestMethod.POST)
    public Result<Boolean> addUserTreeRel(@RequestBody UserNodeInfo bookVO) {
        return JsonResultBuilder.simpleSucc(userNodeService.addUserTreeRel(null));
    }
}
