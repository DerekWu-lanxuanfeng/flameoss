package com.flame.flameoss.global.web;

import com.flame.flameoss.common.json.JacksonUtil;
import com.flame.flameoss.global.web.constants.GlobalConstants;
import com.flame.flameoss.global.web.constants.GlobalErrorCode;
import com.flame.flameoss.global.web.dto.RespDTO;
import com.flame.flameoss.global.web.dto.RespFactory;
import com.flame.flameoss.modules.user.entiry.User;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 深圳市烈焰时代科技有限公司 版权所有
 *
 * @Name: flameoss - LoginCheckInterceptor.class
 * @Description: // 登录验证过滤器
 * @Create: DerekWu on 2018/2/10 17:13
 * @Version: V1.0
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        boolean result = false;
        if (session != null && session.getAttribute(GlobalConstants.USER_SESSION_KEY) != null) {
            User user = (User)session.getAttribute(GlobalConstants.USER_SESSION_KEY);
            if (user != null) {
                result = true;
            }
        }
        if (!result) {
            RespDTO respDTO = RespFactory.buildErrMsg(GlobalErrorCode.LOGIN_TIME_OUT, "登录超时，请重新登录！");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.setStatus(HttpStatus.SC_OK);
            PrintWriter out = response.getWriter();
            out.print(JacksonUtil.encode(respDTO));
            out.flush();
            out.close();
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
