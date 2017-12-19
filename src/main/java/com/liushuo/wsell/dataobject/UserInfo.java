package com.liushuo.wsell.dataobject;

import lombok.Data;

/**
 * Create by liushuo on 2017/12/18.
 */
@Data
public class UserInfo {
    private  String openid;
    private  String nickname;
    private  String sex;
    private  String city;
    private  String country;
    private  String province;
    private  String headimgurl;
    private  String privilege;

}
