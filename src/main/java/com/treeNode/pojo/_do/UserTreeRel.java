package com.treeNode.pojo._do;

import lombok.Data;

import java.util.Date;

/**
 * 用户和树的关联表实体类
 */
@Data
public class UserTreeRel {
    private Integer id;

    /**
     * 用户表id
     */
    private Integer userId;

    /**
     * 树表id
     */
    private Integer nodeId;

    private Integer active;

    private Date createTime;

    private Date updateTime;

}
