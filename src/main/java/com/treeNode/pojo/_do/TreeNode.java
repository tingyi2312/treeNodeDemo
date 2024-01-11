package com.treeNode.pojo._do;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 树表实体类
 */
@Data
public class TreeNode implements Serializable {
    private Integer id;

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

    private Integer active;

    private Date createTime;

    private Date updateTime;

}
