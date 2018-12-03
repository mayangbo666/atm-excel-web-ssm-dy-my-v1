package com.github.mayangbo666.service;

import com.github.mayangbo666.dao.CardDao;
import com.github.mayangbo666.entity.Card;
import com.github.mayangbo666.exception.MyException;
import com.github.mayangbo666.vo.AjaxVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAccountService {

    @Autowired
    private CardDao cardDao;

    public void openAccount(int userId, String cardNum, String password){
        if (StringUtils.isAnyBlank(cardNum, password)){
            throw new MyException("开户卡号, 密码不能为空");
        }
        List<Card> cardList = cardDao.selectCardByCardNum(cardNum);
        if (0 < cardList.size()){
            throw new MyException("此卡号已存在");
        }
        int effectRows = cardDao.insertCardByCardNum(userId, cardNum, password);
        if (1 != effectRows){
            throw new MyException("开户失败");
        }
    }
}
