package model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -528999092511821526L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    private String username;

    /**
     * 登录账号
     */
    private String userAccount;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别 1男 0 女
     */
    private Integer gender;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态  0-正常
     */
    private Integer userStatus;

    /**
     * 创建时间(数据插入时间)
     */
    private Date createTime;

    /**
     * 更新时间(数据更新时间)
     */
    private Date updateTime;

    /**
     * 是否删除 0正常 1（逻辑删除）
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 0-普通用户 1-管理员
     */
    private Integer userRole;



}