package com.github.mayangbo666.controller;

import com.github.mayangbo666.entity.User;
import com.github.mayangbo666.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;


public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected User getUser(HttpSession session) {

//        Object obj = session.getAttribute("user");
//        if (null == obj){
//            throw new MyException("用户未登录");
//        }
//        if (!(obj instanceof User)){
//            throw new MyException("用户未登录");
//        }
//        return (User)obj;

        return new User();
    }

    protected int getUserId(HttpSession session) {

//        Object obj = session.getAttribute("user");
//        if (null == obj){
//            throw new MyException("用户未登录");
//        }
//        if (!(obj instanceof User)){
//            throw new MyException("用户未登录");
//        }
//        User user = (User)obj;
//        int id = user.getId();
//        return id;

        return 1;
    }

    protected String getUserName(HttpSession session) {

//        Object obj = session.getAttribute("user");
//        if (null == obj){
//            throw new MyException("用户未登录");
//        }
//        if (!(obj instanceof User)){
//            throw new MyException("用户未登录");
//        }
//        User user = (User)obj;
//        return user.getUserName();

        return "tom";
    }

    protected void saveUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }

}
