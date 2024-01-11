package com.treeNode.pojo._do;

import lombok.Data;

import java.util.Date;

/**
 * 用户表实体类
 */
@Data
public class User {
    private Integer id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户类型
     */
    private String userType;

    private Integer active;

    private Date createTime;

    private Date updateTime;

}
