package com.aidjajd.usercenter.service;


import com.aidjajd.usercenter.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRegisterTest {

    @Autowired
    private UserService userService;

    // 不能包含空数据
    @Test
    public void notNullTest(){
        String userAccount = "14195939651";
        String userPassword = "";
        String checkPassword = "123456789";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,l);
    }
    // 账号不能有特殊字符
    @Test
    public void specialCharacter(){
        String userAccount = "1419593965@";
        String userPassword = "123456789";
        String checkPassword = "123456789";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,l);
    }
    // 账号长度不能小于4位
    @Test
    public void accountLen(){
        String userAccount = "123";
        String userPassword = "123456789";
        String checkPassword = "123456789";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,l);
    }
    // 密码长度不能小于8位
    @Test
    public void passwordLen(){
        String userAccount = "1419593965";
        String userPassword = "1234567";
        String checkPassword = "1234567";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,l);
    }
    // 密码和校验密码相同
    @Test
    public void pwdEqCheckPwd(){
        String userAccount = "1419593965";
        String userPassword = "123456789";
        String checkPassword = "1234567";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,l);
    }
    // 账号不能重复
    @Test
    public void accountRepeat(){
        String userAccount = "aidjajd";
        String userPassword = "123456789";
        String checkPassword = "123456789";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,l);
    }
    // 按规则注册成功
    @Test
    public void success(){
        String userAccount = "yeguo5";
        String userPassword = "123456789";
        String checkPassword = "123456789";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(15,l);
    }

}
