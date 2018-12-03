package com.github.mayangbo666.controller;

import com.github.mayangbo666.entity.User;
import com.github.mayangbo666.exception.MyException;
import com.github.mayangbo666.service.UserService;
import com.github.mayangbo666.vo.AjaxVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController{
//  extends BaseController
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public AjaxVO login(HttpSession session, String loginCode, String password){
        try {
//            logger.debug("login...");
            User user =  userService.login(session, loginCode, password);
            session.setAttribute("user", user);
            return AjaxVO.success();
        }catch (MyException me){
//            logger.info(me.getMessage());
            return AjaxVO.failed(me.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return AjaxVO.failed("请联系客服");
        }
    }

    @ResponseBody
    @RequestMapping("/registVerify")
    public AjaxVO toRegistVerify(String loginCode, String password, String userName){
        try {
            userService.registVerify(loginCode, password, userName);
            return AjaxVO.success();
        }catch (MyException me){
            return AjaxVO.failed(me.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return AjaxVO.failed("请联系客服");
        }
    }

    @ResponseBody
    @RequestMapping("/getUser")
    public AjaxVO getUser2(HttpSession session){
        User user = getUser(session);
        if (null == user){
            return AjaxVO.failed("当前用户会话已失效");
        }
        User returnUser = new User();
        returnUser.setUserName(user.getUserName());
        return AjaxVO.success(returnUser);
    }

    @ResponseBody
    @RequestMapping("/logout")
    public AjaxVO logout(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (null == user){
            return AjaxVO.failed("当前用户会话已失效");
        }
        session.removeAttribute("user");
        return AjaxVO.success();
    }

}
