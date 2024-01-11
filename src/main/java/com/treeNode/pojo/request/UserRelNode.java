package com.treeNode.pojo.request;

import lombok.Data;

@Data
public class UserRelNode {
    /**
     * 关联表id
     */
    private Integer id;

    /**
     * 用户表id
     */
    private Integer userId;

    /**
     * 树表id
     */
    private Integer nodeId;

    /**
     * 树名称
     */
    private String treeName;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 父节点id
     */
    private Integer parentId;
}
