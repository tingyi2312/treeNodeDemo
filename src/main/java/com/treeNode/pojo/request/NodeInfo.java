package com.treeNode.pojo.request;

import lombok.Data;

import java.util.List;

@Data
public class NodeInfo {
    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 子节点信息
     */
    private List<NodeInfo> subNodeList;
}
