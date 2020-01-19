package com.dahantc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Kevin Zhu on 2019/1/26 18:16 .
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String Id;

    private String username;

    private String password;

    private String openid;

    private Integer role;


}
