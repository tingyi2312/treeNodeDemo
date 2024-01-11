package com.treeNode.pojo.request;

import lombok.Data;

/**
 * 用户请求
 */
@Data
public class UserNodeRequest {
    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 树名称
     */
    private String treeName;

    /**
     * 节点名称
     */
    private NodeInfo nodeInfo;
}
