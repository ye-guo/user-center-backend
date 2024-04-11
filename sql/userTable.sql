create table user
(
    id            bigint auto_increment comment '主键'
        primary key,
    username      varchar(128)                               null comment '昵称',
    user_account  varchar(256)                               null comment '登录账号',
    avatar_url    varchar(1024)                              null comment '头像',
    gender        tinyint                                    null comment '性别',
    user_password varchar(512)                               not null comment '用户密码',
    phone         varchar(128)                               null comment '电话',
    email         varchar(512)                               null comment '邮箱',
    user_status   int              default 0                 not null comment '用户状态  0-正常',
    create_time   datetime         default CURRENT_TIMESTAMP not null comment '创建时间(数据插入时间)',
    update_time   datetime         default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间(数据更新时间)',
    is_Delete     tinyint          default 0                 not null comment '是否删除 0 1（逻辑删除）',
    user_role     int(11) unsigned default 0                 not null comment '0-普通用户 1-管理员'
)
    comment '用户表' engine = InnoDB;

