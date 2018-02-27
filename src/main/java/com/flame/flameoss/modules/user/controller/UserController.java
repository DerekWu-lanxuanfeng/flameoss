package com.flame.flameoss.modules.user.controller;

import com.flame.flameoss.global.web.constants.GlobalConstants;
import com.flame.flameoss.global.web.constants.GlobalErrorCode;
import com.flame.flameoss.global.web.dto.RespDTO;
import com.flame.flameoss.global.web.dto.RespFactory;
import com.flame.flameoss.modules.user.entiry.User;
import com.flame.flameoss.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    private RedisTemplate<String, User> redisTemplate = RedisTemplateFactory.buildJackson(User.class);

    @RequestMapping("/login")
    public RespDTO login(HttpServletRequest request, String userAccount, String userPass) {
        User user = userService.getUserByAccount(userAccount);
        User user1 = (User) request.getSession().getAttribute(GlobalConstants.USER_SESSION_KEY);
//        RedisTemplate redisTemplate = (RedisTemplate)SpringContextUtils.getBean("redisTemplate");
//        RedisTemplate sessionRedisTemplate = (RedisTemplate)SpringContextUtils.getBean("sessionRedisTemplate");
        if (user == null) {
            return RespFactory.buildErrMsg(GlobalErrorCode.ACCOUNT_NOT_EXISTS, "账号不存在！");
        }
        if (userPass != null && Objects.equals(userPass, user.getUserPass())) {
            request.getSession().setAttribute(GlobalConstants.USER_SESSION_KEY, user);
            return RespFactory.build(user);
        }
//        request.getSession().setAttribute(GlobalConstants.USER_SESSION_KEY, user);
//        redisTemplate.opsForValue().set("sefefdsfdsf:"+user.getUserId(), user);
        return RespFactory.buildErrMsg(GlobalErrorCode.PASSWORD_ERROR, "密码错误！");
    }
}
