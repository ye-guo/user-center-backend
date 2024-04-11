package com.aidjajd.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import model.domain.User;
import model.domain.request.dto.SafetyUserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
* @author yeguo
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-09-19 19:44:03
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户的 id
     */

    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     * @param userAccount  用户账号
     * @param userPassword 玉户密码
     * @param req
     * @return 该用户对象
     */
    SafetyUserDTO userLogin(String userAccount, String userPassword, HttpServletRequest req);

    User selectById(long id);
    SafetyUserDTO geSafetytUser(User user);

    ArrayList<SafetyUserDTO> selectAll();
}
