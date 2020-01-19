package com.dahantc.controller;

import com.dahantc.constant.CookieConstant;
import com.dahantc.constant.RedisConstant;
import com.dahantc.dataobject.UserInfo;
import com.dahantc.enums.ResultEnum;
import com.dahantc.enums.RoleEnum;
import com.dahantc.service.UserService;
import com.dahantc.utils.CookieUtil;
import com.dahantc.utils.ResultVOUtil;
import com.dahantc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kevin Zhu on 2019/1/26 18:43 .
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        //1.openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return ResultVOUtil.error(ResultEnum.LOGINFAIL);
        }
        //2.判断角色
        if (!Objects.equals(RoleEnum.BUYER.getCode(), userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLEFAIL);
        }
        //3.cookie里设置openid=abc
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.EXPIRE);
        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletRequest request, HttpServletResponse response) {
        //先从缓存中取
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(Objects.nonNull(cookie) && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))){
            return ResultVOUtil.success();
        }
        //1.openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return ResultVOUtil.error(ResultEnum.LOGINFAIL);
        }
        //2.判断角色
        if (!Objects.equals(RoleEnum.SELLER.getCode(), userInfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLEFAIL);
        }
        //3.redis设置key=UUID,value=xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), openid, expire, TimeUnit.SECONDS);
        //4.cookie里设置token=UUID
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return ResultVOUtil.success();
    }
}
