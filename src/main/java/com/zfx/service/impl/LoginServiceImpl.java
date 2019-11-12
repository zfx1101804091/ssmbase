package com.zfx.service.impl;


import com.zfx.mapper.UserMapper;
import com.zfx.pojo.User;
import com.zfx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
@Service
public class LoginServiceImpl implements LoginService {
 
    @Autowired
    private UserMapper userMapper;
 
    @Override
    public Map<String, Object> login(String loginname, String password) {
        HashMap<String, Object> mm = new HashMap<>();
      /*  UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginnameEqualTo(loginname);*/
        List<User> users = userMapper.login(loginname);//根据用户名在数据库查找对应的用户
        System.out.println(users.toString());
        String msg = "";
        if ((users.size() == 0)) {//如果size=0就是没找到这个用户
            mm.put("status", "401");
            mm.put(msg, "用户不存在!");
        }
         if ((users.size() > 1)) {//如果size>1就是这数据有问题了，不应该存在两个一样的用户名
            mm.put("status", "401");
            mm.put(msg, "error!");
        }
 
        User user = users.get(0);//取集合里第一个
        if (!password.equals(user.getPassword())) {
            mm.put("status", "401");
            mm.put(msg, "密码错误！");
        } else {
            mm.put("status", "200");
            mm.put(msg, "登陆成功!");
        }
        return mm;//把map集合里的状态码和信息提示返回给controller
    }
}