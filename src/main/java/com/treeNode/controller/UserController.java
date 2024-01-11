package com.treeNode.controller;

import com.treeNode.pojo.Response.UserNodeResp;
import com.treeNode.pojo.request.UserNodeRequest;
import com.treeNode.service.UserService;
import com.treeNode.util.response.BaseRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 根据id查询
     * @param bookVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectUser", method = RequestMethod.POST)
    public BaseRsp<UserNodeResp> selectUser(@RequestBody UserNodeRequest bookVO) {
        userService.saveUser(null);
        return  null;
    }

}
