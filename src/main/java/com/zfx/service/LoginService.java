package com.zfx.service;
 
import java.util.Map;
 
public interface LoginService {
    //loginname是登陆名称
    //password是密码
    Map<String,Object> login(String loginname, String password);
}