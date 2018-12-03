package com.github.mayangbo666.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  该项目主要功能可有 注册/登录/退出 存/取/转/下载流水/上传流水等
 */
@Controller
public class AtmViewController {

    //    @ResponseBody
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/toMainPage")
    public String toMainPage() {
        return "mainPage";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/toOpenAccount")
    public String toOpenAccount() {
        return "openAccount";
    }

    @RequestMapping("/toDeposit")
    public String toDeposit() {
        return "deposit";
    }
//    @RequestMapping("/toWxLogin")
//    public String toWxLogin() {
//        return "wxAuthen";
//    }

    @RequestMapping("/toFlow")
    public String toFlow() {
        return "flow";
    }

    @RequestMapping("/testExcel")
    public String excel() {
        return "testExcel";
    }
}
