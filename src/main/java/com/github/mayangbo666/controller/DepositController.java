package com.github.mayangbo666.controller;

import com.github.mayangbo666.exception.MyException;
import com.github.mayangbo666.service.DepositService;
import com.github.mayangbo666.vo.AjaxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class DepositController extends BaseController{

    @Autowired
    private DepositService depositService;

    @ResponseBody
    @RequestMapping("/deposit")
    public AjaxVO deposit(HttpSession session, String cardNum, String password, String amount){
        try {
            int userId = getUserId(session);
            depositService.deposit(userId, cardNum, password, amount);
            return AjaxVO.success();
        }catch (MyException me){
            return AjaxVO.failed(me.getMessage());
        }catch (Exception e){
            logger.error("Deposit Exception...", e);
            return AjaxVO.failed("请联系客服");
        }
    }
}
