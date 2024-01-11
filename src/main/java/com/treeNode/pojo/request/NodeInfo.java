package com.treeNode.pojo.request;

import lombok.Data;

import java.util.List;

@Data
public class NodeInfo {
    /**
     * 节点id
     */
    private Integer nodeId;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 父节点id
     */
    private Integer parentId;

    /**
     * 子节点信息
     */
    private List<NodeInfo> subNodeList;

    public NodeInfo() {
    }

    public NodeInfo(Integer nodeId, String nodeName, Integer parentId,List<NodeInfo> subNodeList) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.parentId = parentId;
        this.subNodeList=subNodeList;
    }
}
