package com.aidjajd.usercenter.interceptor;

import com.aidjajd.usercenter.common.ErrorCode;
import com.aidjajd.usercenter.constant.UserConstant;
import com.aidjajd.usercenter.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class GlobalPreInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // 参数设置为false，如果Session不存在则返回null
        if (session == null || session.getAttribute(UserConstant.USER_LOGIN_STATE) == null) {
            // Session不存在或用户未登录，可以进行相应的处理
           throw new BusinessException(ErrorCode.NOT_LOGIN,"未登录");
        }
        return true;
    }
}
