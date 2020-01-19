package com.dahantc.repository;

import com.dahantc.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kevin Zhu on 2019/1/26 18:20 .
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenid(String openid);
}
