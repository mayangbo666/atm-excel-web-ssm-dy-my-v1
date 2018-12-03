package com.github.mayangbo666.service;

import com.github.mayangbo666.dao.CardDao;
import com.github.mayangbo666.dao.FlowDao;
import com.github.mayangbo666.dto.FlowDTO;
import com.github.mayangbo666.entity.Card;
import com.github.mayangbo666.entity.Flow;
import com.github.mayangbo666.enums.FlowTypeEnum;
import com.github.mayangbo666.exception.MyException;
import com.github.mayangbo666.util.PageHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlowService {


    @Autowired
    private CardService cardService;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private FlowDao flowDao;

    private static final Logger logger = LoggerFactory.getLogger(FlowService.class);

    public PageHolder<FlowDTO> listFlow(String cardNum, String curPage, String pageNum) {

//        cardService.checkCard(userId, cardNum, password);

        if (StringUtils.isAnyBlank(curPage, pageNum)) {
            MyException me = new MyException("获取当前页, 每页条数失败");
            logger.error(me.getMessage(), me);
            throw new MyException("请联系客服");
        }

        int curPageI = 1;
        int pageNumI = 2;
        try {
            curPageI = Integer.valueOf(curPage);
            pageNumI = Integer.valueOf(pageNum);
        } catch (NumberFormatException nfe) {
            String errorMsg = "";
            logger.error(errorMsg, nfe);
            throw new MyException("查询流水失败");
        }

        int totalCount = flowDao.countFlowByCardNum(cardNum);

        PageHolder<FlowDTO> flowDTOPageHolder = new PageHolder<>(curPageI, pageNumI, totalCount);
        int offset = flowDTOPageHolder.getOffset();

        List<Flow> flowList = flowDao.listFlowByCardNum(cardNum, offset, pageNumI);

        int totalPage = flowDTOPageHolder.getTotalPage();

        List<FlowDTO> flowDTOList;

        if (curPageI < totalPage){
            flowDTOList = new ArrayList<>(pageNumI);
        }else{
            flowDTOList = new ArrayList<>(totalCount - pageNumI * (curPageI - 1));
        }

        for (Flow flow : flowList) {
            FlowDTO flowDTO = new FlowDTO();
            flowDTOList.add(flowDTO);

            flowDTO.setCardNum(cardNum);
            flowDTO.setTradeAmount(flow.getTradeAmount());

            flowDTO.setTradeType(FlowTypeEnum.getFlowTypeEnum(flow.getTradeType()).getV());

            Date date = flow.getCreateTime();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDateStr = df.format(date);
            flowDTO.setCreateTime(createDateStr);
        }

        flowDTOPageHolder.setData(flowDTOList);
        return flowDTOPageHolder;
    }
}
