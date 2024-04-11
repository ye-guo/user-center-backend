package com.aidjajd.usercenter;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import com.aidjajd.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//@SpringBootTest
public class MainTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test02(){
        User user = new User();
        user.setUsername("jsp");
        user.setUserAccount("aidjajd");
        user.setAvatarUrl("https://files.catbox.moe/0tn6uf.png");
        user.setGender(0);
        user.setUserPassword("123456");
        user.setPhone("17337904072");
        user.setEmail("aidjajd@163.com");
        user.setUserStatus(0);
        int result = userMapper.insert(user);
        Assertions.assertEquals(1,result);
    }

    @Test
    public void test03(){
        String content = "test中文";
        SymmetricCrypto sm4 = SmUtil.sm4();

        String encryptHex = sm4.encryptHex(content);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("SM4加密："+encryptHex);
        System.out.println("SM4解密："+decryptStr);
    }

    @Test
    public void test04(){
// 密钥
        byte[] key = "1234567890".getBytes();

        String token = JWT.create()
                .setPayload("sub", "1234567890")
                .setPayload("name", "looly")
                .setPayload("admin", true)
                .setKey(key)
                .sign();
        log.info(token);

    }

    @Test
    public void test6(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2MjQwMDQ4MjIsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV_op5LoibLkuozlj7ciLCJzeXNfbWVudV8xIiwiUk9MRV_op5LoibLkuIDlj7ciLCJzeXNfbWVudV8yIl0sImp0aSI6ImQ0YzVlYjgwLTA5ZTctNGU0ZC1hZTg3LTVkNGI5M2FhNmFiNiIsImNsaWVudF9pZCI6ImhhbmR5LXNob3AifQ." +
                "aixF1eKlAKS_k3ynFnStE7-IRGiD5YaqznvK2xEjBew";

        JWTUtil.verify(token, "123456".getBytes());
        JWT jwt = JWTUtil.parseToken(token);

        System.out.println(jwt.getHeader(JWTHeader.TYPE));
        jwt.getPayload("sub");

    }
    @Test
    public void test7(){


    }

}
