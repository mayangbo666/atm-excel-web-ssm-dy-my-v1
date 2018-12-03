package com.github.mayangbo666.service;

import com.github.mayangbo666.dao.CardDao;
import com.github.mayangbo666.entity.Card;
import com.github.mayangbo666.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardDao cardDao;

    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    public List<Card> getCards(int userId) {
        List<Card> cardList = cardDao.selectCardsByUserId(userId);

        if (cardList.isEmpty()) {
            throw new MyException("请先进行开户");
        }
        return cardList;
    }

    public void checkCard(int userId, String cardNum, String password) {

        if (StringUtils.isAnyBlank(cardNum, password)) {
            throw new MyException("卡号, 密码不能为空");
        }

        //根据卡号查密码
        List<Card> cardList = cardDao.selectCardByCardNum(cardNum);
        if (cardList.isEmpty()) {
            throw new MyException("该卡不存在");
        }
        if (1 != cardList.size()) {
            logger.error("数据库中存在多张卡号: " + cardNum);
            throw new MyException("请联系客服");
        }
        Card card = cardList.get(0);
        if (!password.equals(card.getPassword())) {
            throw new MyException("卡密码不正确");
        }
        if (userId != card.getUserId()) {
            throw new MyException("请对自己的银行卡进行操作");
        }
    }
}
