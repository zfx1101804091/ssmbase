package com.zfx.controller;
 
import com.zfx.pojo.User;
import com.zfx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController {
    
    @Autowired
    private LoginService loginService;
 
    @RequestMapping("toLogin")
    public String toLogin() {
        return "login.jsp";
    }

    @RequestMapping("login")
    public String login(User user, Model model) {
        String loginname = user.getUsername();
        String password = user.getPassword();
        Map<String, Object> map = loginService.login(loginname, password);
        if (map.get("status").equals("200")) {
            return "system/ceshi.jsp";
        } else {
            model.addAttribute("error", map.get("msg"));
            return "login.jsp";
        }
    }
    
    
}