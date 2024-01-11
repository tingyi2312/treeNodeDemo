CREATE TABLE 'user' (
    'id' bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    'user_name' varchar(32) NOT NULL COMMENT '姓名',
	'user_type' varchar(32) NOT NULL COMMENT '用户类型(1:管理员, 2:普通用户)',
	'active' int not null COMMENT '是否激活(1:是; 0:否)',
	'create_time' timestamp default CURRENT_TIMESTAMP not null COMMENT '创建时间',
    'update_time' timestamp default CURRENT_TIMESTAMP not null COMMENT '更新时间',
    PRIMARY KEY('id') USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户表';

CREATE TABLE 'tree_node' (
    'id' bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    'tree_name' varchar(32) NOT NULL COMMENT '树名称',
    'node_name' varchar(32) NOT NULL COMMENT '节点名称',
	'parent_id' bigint(32) COMMENT '父节点id',
	'active' int not null COMMENT '是否激活(1:是; 0:否)',
	'create_time' timestamp default CURRENT_TIMESTAMP not null COMMENT '创建时间',
    'update_time' timestamp default CURRENT_TIMESTAMP not null COMMENT '更新时间',
    PRIMARY KEY('id') USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '树表';


CREATE TABLE 'user_tree_rel' (
    'id' bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    'user_id'  bigint(32) NOT NULL COMMENT '用户表id',
	'node_id'  bigint(32) NOT NULL COMMENT '树表id',
	'active' int not null COMMENT '是否激活(1:是; 0:否)',
	'create_time' timestamp default CURRENT_TIMESTAMP not null COMMENT '创建时间',
    'update_time' timestamp default CURRENT_TIMESTAMP not null COMMENT '更新时间',
    PRIMARY KEY('id') USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户和树的关联表';
