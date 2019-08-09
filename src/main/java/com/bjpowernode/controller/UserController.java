package com.bjpowernode.controller;

import com.bjpowernode.commons.CommonsConst;
import com.bjpowernode.commons.CommonsReturnObject;
import com.bjpowernode.model.User;
import com.bjpowernode.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/checkLoginName.do")
    public @ResponseBody Object loginNameCheck(String loginName) {
        CommonsReturnObject returnObject = new CommonsReturnObject();
        User user=null;
        if ("".equals(loginName)||loginName==null){
            returnObject.setCode(CommonsConst.ERROR);
            returnObject.setErrorMessage("登录名不能为空*****");
            returnObject.setData(null);
            return returnObject;
        }else {
            user=userService.findUserByLoginName(loginName);
        }
        //程序进入if表示该用户名已存在,此时需要给用户错误信息提示
        if (user!=null){
            returnObject.setCode(CommonsConst.ERROR);
            returnObject.setErrorMessage("用户账号已存在,请更换!");
            returnObject.setData(null);
            return returnObject;
        }
            returnObject.setCode(CommonsConst.OK);
            returnObject.setErrorMessage(null);
            returnObject.setData("用户账号可以使用");
            return returnObject;
    }

    @RequestMapping("/user/private/add.do")
    public String addUser(User user,Model model){
        Integer result=0;
        if ("".equals(user.getLoginName())||user.getLoginName()==null){
            model.addAttribute("errorMessage", "登录名不能为空");
            return "user_Add";
        }else {
            result=userService.add(user);
        }
        switch (result){
            case 1:
                model.addAttribute("errorMessage", "登录名已存在,请更换!!!");
                model.addAttribute("user", user);
                return "user_Add";
        }
        return "redirect:/user/private/find.do";
    }

    @RequestMapping("/user/private/find.do")
    public String findUserAll(Model model){
        List<User> userList=userService.findUserAll();
        model.addAttribute("userList",userList);
        return "user_Show";
    }
    @RequestMapping("/login.do")
    public String login(User user,String isAutoLogin, Model model, HttpSession session,HttpServletResponse response){
        //isAutoLogin获取checkbox的name属性的值,如name=isAutoLogin,value="yes"
        return this.myLogin(user,isAutoLogin,model,session,response);
    }

    @RequestMapping("/checkLogin.do")
    public String checkLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model){
        //1.获得cookies,是一个数组
        Cookie cookies[]=request.getCookies();
        //System.out.println(cookies.length);
        //2.判断cookie是否有内容
        //进入if:如果cookies为null表示使用浏览器第一次访问我们的服务器
        // 如果Cookie数组的长度小于2表示Cookie没有足够账号和密码数据,这时我们需要转向到登录页面完成用户的登录
        //注意,cookies数组里有默认的cookie名字和值
        if(cookies==null||cookies.length<2){
            return "login";
        }
        //3.程序到了这里表示Cookie中可能拥有账号和密码,取出cookies里的用户名和密码
        User user = new User();
        for (Cookie cookie:cookies){
            //执行此if表示当前的Cookie的名字为账号,需要将数据存入user对象中
            if ("loginName".equals(cookie.getName())){
                user.setLoginName(cookie.getValue());
            //执行此if表示当前的Cookie的名字为密码,需要将数据存入user对象中
            }else if ("password".equals(cookie.getName())){
                user.setPassword(cookie.getValue());
            }
        }
        //4.程序到了这里,我们遍历了请求中的所有的Cookie,这时User对象中可能拥有账号和密码,
        // 此时需要判断用户和密码是否符合要求
        // 如果程序执行了if,则表示用户名或密码为null或为""表示Cookie没有完整的用户登录数据需要用户完成登录
        if (user.getLoginName()==null||"".equals(user.getLoginName())||
            user.getPassword()==null||"".equals(user.getPassword())){
            return "login";
        }
        //5.程序进入到这里,说明cookies里的登录名和密码不为空,则需要进一步验证码登录名和密码的正确性,
        // 此时的验证和第一次登录验证是一样的,所以需要重复执行登录验证的代码,那么我们需要抽象出登录验证的方法
        return this.myLogin(user,"yes",model,session,response);
    }

    public String myLogin(User user,String isAutoLogin, Model model, HttpSession session,HttpServletResponse response){
        Integer result=userService.login(user);
        switch (result){
            case 1:
                model.addAttribute("errorMessage", "用户名错误,请重新输入!");
                return "login";
            case 2:
                model.addAttribute("errorMessage", " 登录密码错误,请重新输入!");
                return "login";
        }
        session.setAttribute("userSession", user);
        // 判断用户是否勾选十天免登陆,如果勾选则记录cookie
        if (isAutoLogin!=null){
            Cookie cookieLoginName = new Cookie("loginName", user.getLoginName());
            Cookie cookiePassWord = new Cookie("password",user.getPassword());
            //设置cooeie的有效期
            cookieLoginName.setMaxAge(60*60*24*10);
            cookiePassWord.setMaxAge(60*60*24*10);
            //设置Cookie的访问路径为根路径
            cookieLoginName.setPath("/");
            cookiePassWord.setPath("/");
            //把cookie推给客户端浏览器并保存在本地机器的硬盘中
            response.addCookie(cookieLoginName);
            response.addCookie(cookiePassWord);
        }
        return "redirect:/index.jsp";
    }
}
