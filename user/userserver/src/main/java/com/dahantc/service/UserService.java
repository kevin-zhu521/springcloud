package com.dahantc.service;

import com.dahantc.dataobject.UserInfo;

/**
 * Created by Kevin Zhu on 2019/1/26 18:23 .
 */
public interface UserService {

    UserInfo findByOpenid(String openid);
}
