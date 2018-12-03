package com.github.mayangbo666.service;

import com.github.mayangbo666.dao.UserDao;
import com.github.mayangbo666.entity.User;
import com.github.mayangbo666.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User login(HttpSession session, String loginCode, String password){

        if (StringUtils.isAnyBlank(loginCode, password)){
             throw new MyException("用户名或密码不能为空");
        }

        User user = userDao.getUserByLoginCode(loginCode);

        if (null == user){
           throw new MyException("用户名或密码错误");
        }

        if (!password.equals(user.getPassword())){
            throw new MyException("用户名或密码错误");
        }
        session.setAttribute("user", user);
        return user;
    }

    public void registVerify(String loginCode, String password, String userName){
        if (StringUtils.isAnyBlank(loginCode, password, userName)){
            throw new MyException("账号,用户名或密码不能为空");
        }
        User user = userDao.getUserByLoginCode(loginCode);
        if (null != user){
            throw new MyException("账号已存在");
        }
        int effectRowNum = userDao.insertUser(loginCode, password, userName);
        if(1 != effectRowNum){
            throw new RuntimeException();
        }
    }
}
