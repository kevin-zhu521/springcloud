package com.dahantc.service.impl;

import com.dahantc.dataobject.UserInfo;
import com.dahantc.repository.UserInfoRepository;
import com.dahantc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kevin Zhu on 2019/1/26 18:24 .
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
