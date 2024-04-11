package com.aidjajd.usercenter.service;

import model.domain.User;
import model.domain.request.dto.SafetyUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
public class UserLoginTest {

    @Autowired
    private UserService userServiceImpl;
    HttpServletRequest req = null;
    // 数据不能为空
    @Test
    public void isNotNull(){

        String userAccount = "123456";
        String userPassword = "";
        SafetyUserDTO user = userServiceImpl.userLogin(userAccount,userPassword, req);
        Assertions.assertNull(user);
    }
    // 账号长度不小于4位
    @Test
    public void accountLen(){
        String userAccount = "123";
        String userPassword = "123456";
        SafetyUserDTO user = userServiceImpl.userLogin(userAccount,userPassword, req);
        Assertions.assertNull(user);
    }
    // 密码不小于8位
    @Test
    public void passwordLen(){
        String userAccount = "yeguo1";
        String userPassword = "1234567";
        SafetyUserDTO user = userServiceImpl.userLogin(userAccount,userPassword, req);
        Assertions.assertNull(user);
    }
    // 账号不能包含特殊字符
    @Test
    public void specialCharacter(){
        String userAccount = "yeguo1@";
        String userPassword = "12345678";
        SafetyUserDTO user = userServiceImpl.userLogin(userAccount,userPassword, req);
        Assertions.assertNull(user);
    }
    // 查询用户成功
    @Test
    public void success(){
        String userAccount = "yeguo5";
        String userPassword = "123456789";
        SafetyUserDTO user = userServiceImpl.userLogin(userAccount,userPassword, req);
        System.out.println(user);
        Assertions.assertNotNull(user);
    }
}
