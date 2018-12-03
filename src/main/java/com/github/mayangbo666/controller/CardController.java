package com.github.mayangbo666.controller;

import com.github.mayangbo666.entity.Card;
import com.github.mayangbo666.exception.MyException;
import com.github.mayangbo666.service.CardService;
import com.github.mayangbo666.vo.AjaxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CardController extends BaseController{

    @Autowired
    private CardService cardService;

    @ResponseBody
    @RequestMapping("/listBankCard")
    public AjaxVO listBankCard(HttpSession session){
        try {
            int userId = getUserId(session);
            List<Card> cardList = cardService.getCards(userId);
            return AjaxVO.success(cardList);
        }catch (MyException me){
            return AjaxVO.failed(me.getMessage());
        }catch (Exception e){
            logger.error("加载银行卡信息异常", e);
            return AjaxVO.failed("fail");
        }
    }


}
