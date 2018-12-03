package com.github.mayangbo666.controller;

import com.github.mayangbo666.exception.MyException;
import com.github.mayangbo666.service.OpenAccountService;
import com.github.mayangbo666.vo.AjaxVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class OpenAccountController extends BaseController{

    @Autowired
    private OpenAccountService openAccountService;

    @ResponseBody
    @RequestMapping("/openAccount")
    public AjaxVO openAccount(HttpSession session, String cardNum, String password){

        try{
            int userId = getUserId(session);
            if (0 == userId){
                return AjaxVO.failed("请先进行登录");
            }
            openAccountService.openAccount(userId, cardNum, password);
            return AjaxVO.success();
        }catch (MyException me){
            return AjaxVO.failed(me.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return AjaxVO.failed("请联系客服");
        }
    }
}
