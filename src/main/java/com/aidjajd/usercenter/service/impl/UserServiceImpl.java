package com.aidjajd.usercenter.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.aidjajd.usercenter.common.ErrorCode;
import com.aidjajd.usercenter.constant.UserConstant;
import com.aidjajd.usercenter.exception.BusinessException;
import com.aidjajd.usercenter.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.aidjajd.usercenter.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import model.domain.User;
import model.domain.request.dto.SafetyUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户service层
* @author yeguo
* @description  针对表【user(用户表)】的数据库操作Service实现
* @createDate  2023-09-19 19:44:03
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    // 密钥
    private static final String secretKey ="野果是帅比y";
    private static final byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
    // sm4加密
    private static final SymmetricCrypto sm4 = new SymmetricCrypto("SM4",key);

    @Autowired
    private UserMapper userMapper;

    // 注册
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {

        if (StrUtil.hasBlank(userAccount,userPassword,checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求包含空数据");
        }
        // 账号不能包含特殊字符
        String regex = "^[a-zA-Z0-9_-]{4,16}$";
        if (!userAccount.matches(regex)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号包含特殊字符");
        }
        // 账号长度不小于4位
        if (userAccount.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度过短");
        }
        // 密码不小于8位
        if (userPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度过短");
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次密码不一致");
        }
        // 账号不能重复
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserAccount,userAccount);
        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.USER_USED_ERROR,"账号已存在");
        }
        // 使用hutool进行密码加密
        String encryptedPassword= sm4.encryptHex(userPassword);

        // 创建用户
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptedPassword);

        // 插入数据库
        int result = userMapper.insert(user);
        // 插入失败返回 -1
        if ( result < 1){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"注册失败，请联系管理员");
        }
        // 插入成功 返回id
        return user.getId();
    }

    // 登录
    @Override
    public SafetyUserDTO userLogin(String userAccount, String userPassword, HttpServletRequest req) {
        // 数据不能为空
        if (StrUtil.hasBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求包含空数据");
        }
        // 账号长度不小于4位
        if (userAccount.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度过短");
        }
        // 密码不小于8位
        if (userPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度过短");
        }
        // 账号不能包含特殊字符
        String regex = "^[a-zA-Z0-9_-]{4,16}$";
        if (!userAccount.matches(regex)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号包含特殊字符");
        }

        // 查询该用户
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserAccount,userAccount);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        // 查询错误
        if (user == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"该用户不存在");
        }

        String password = user.getUserPassword();
        // 查询成功，对数据库用户密码解密和登录用户密码比较
        String decryptedPassword = sm4.decryptStr(password,CharsetUtil.CHARSET_UTF_8);

        if (decryptedPassword.equals(userPassword)){
            // 返回脱敏对象
            SafetyUserDTO safetyUser = geSafetytUser(user);
            // password update_time is_Delete 脱敏
            // 设置session
            HttpSession session = req.getSession();
            session.setAttribute(UserConstant.USER_LOGIN_STATE,safetyUser);

            return safetyUser;
        }
        throw new BusinessException(ErrorCode.PASSWORD_ERROR,"密码错误");
    }

    // 按id查找
    @Override
    public User selectById(long id) {
        User user = userMapper.selectById(id);
        if (user == null)
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"查询失败");
        return user;
    }

    public SafetyUserDTO geSafetytUser(User user) {
        SafetyUserDTO safetyUser = new SafetyUserDTO();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        safetyUser.setUserRole(user.getUserRole());
        return safetyUser;
    }

    @Override
    public ArrayList<SafetyUserDTO> selectAll() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 查询所有User
        ArrayList<User> users = (ArrayList<User>) userMapper.selectList(lambdaQueryWrapper);
        if (users == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"查询错误,请联系管理员");
        }
        // 对每个用户脱敏 返回安全用户信息
        ArrayList<SafetyUserDTO> result = new ArrayList<>();
        for (User user : users) {
            SafetyUserDTO safetyUser = geSafetytUser(user);
            result.add(safetyUser);
        }
        return result;
    }


}




