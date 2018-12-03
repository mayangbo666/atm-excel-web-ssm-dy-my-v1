package com.github.mayangbo666.service;

import com.github.mayangbo666.dao.CardDao;
import com.github.mayangbo666.dao.FlowDao;
import com.github.mayangbo666.entity.Card;
import com.github.mayangbo666.entity.Flow;
import com.github.mayangbo666.enums.FlowTypeEnum;
import com.github.mayangbo666.exception.MyException;
import com.github.mayangbo666.util.MyStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepositService {

    @Autowired
    private CardDao cardDao;

    @Autowired
    private FlowDao flowDao;

    @Transactional(rollbackFor = Exception.class)
    public void deposit(int userId, String cardNum, String password, String amount) {

        if (StringUtils.isAnyBlank(cardNum, password, amount)) {
            throw new MyException("存款卡号, 密码, 金额不能为空");
        }
        List<Card> cardList = cardDao.selectCardByCardNum(cardNum);

        if (cardList.isEmpty()) {
            throw new MyException("卡号不存在");
        }

        Card card = cardList.get(0);
        int userIdDB = card.getUserId();
        if (userId != userIdDB) {
            throw new MyException("请对自己的银行卡进行操作");
        }

        boolean flag = MyStringUtils.isDouble(amount);
        if (!flag) {
            throw new MyException("请输入正确金额");
        }

        int scale = MyStringUtils.scale(amount);
        if (0 != scale && 1 != scale && 2 != scale) {
            throw new MyException("请输入整数或一位小数或两位小数金额");
        }

        String pwdDB = card.getPassword();
        if (!password.equals(pwdDB)) {
            throw new MyException("账号密码不正确");
        }

        String oldAmount = card.getWallet();
        if (StringUtils.isBlank(oldAmount)) {
            throw new MyException("账号金额异常, 请联系客服");
        }
        flag = false;
        flag = MyStringUtils.isDouble(oldAmount);
        if (!flag) {
            throw new MyException("账号金额异常, 请联系客服");
        }

        String newAmount = MyStringUtils.calc(oldAmount, amount, "+");
        newAmount = MyStringUtils.scaleFormat(newAmount, 2);

        int rows = cardDao.updateAmountByCardNum(cardNum, password, newAmount);
        if (1 != rows) {
            throw new MyException("存款失败, 请联系客服");
        }

        int cardId = card.getId();
        String tradeAmount = "+" + MyStringUtils.scaleFormat(amount, 2);
        int tradeType = FlowTypeEnum.deposit_flow.getK();

        Flow flow = new Flow();
        flow.setCardId(cardId);
        flow.setCardNum(cardNum);
        flow.setTradeAmount(tradeAmount);
        flow.setTradeType(tradeType);

        rows = flowDao.save(flow);
        if (1 != rows){
            throw new MyException("存款失败, 请联系客服");
        }
    }
}
