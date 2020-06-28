package com.example.application.controller;

import com.example.application.entity.User;
import com.example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 跳转首页（登录页）
     * @return
     */
    @RequestMapping("/toLogin")
    public String show(){
        return "login";
    }

    /**
     * 登陆操作
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/loginUser", produces = "application/json;charset=utf-8")
    public Boolean login(User user, HttpServletRequest request){
        String userName = user.getUserName();
        String passWord = user.getPassWord();
        User u1 =userService.login(userName,passWord);
        if (u1==null){
            return false;
        }else{
            /**
             * 登录成功后将用户放入session中用于拦截
             */
            request.getSession().setAttribute("session_user",user);
            return true;
        }
    }

    /**
     * 跳转注册页
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    /**
     * 注册操作
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user){
        int su = userService.register(user);
        if(su==0){
            System.out.println("----");
        }
        return "welcome";
    }

    /**
     * 测试未登陆拦截页面
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "welcome";
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/outUser")
    public void outUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session_user");
        response.sendRedirect("/user/toLogin");
    }

}
