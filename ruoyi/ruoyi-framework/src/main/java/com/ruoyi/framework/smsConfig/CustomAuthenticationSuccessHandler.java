package com.ruoyi.framework.smsConfig;
/**
 * @Description:
 * @author cjf
 * @date 2023-07-05
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author cjf
 * @date 2023/7/5
 */

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    TokenService tokenService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");

        AjaxResult ajax = AjaxResult.success();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = tokenService.createToken(loginUser);
        ajax.put(Constants.TOKEN, token);

        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(objectMapper.writeValueAsString(tokenService.createToken(loginUser)));
        response.getWriter().write(objectMapper.writeValueAsString(ajax));
    }

}
