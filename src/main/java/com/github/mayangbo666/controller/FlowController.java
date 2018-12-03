package com.github.mayangbo666.controller;

import com.github.mayangbo666.dto.FlowDTO;
import com.github.mayangbo666.exception.MyException;
import com.github.mayangbo666.service.CardService;
import com.github.mayangbo666.service.FlowService;
import com.github.mayangbo666.util.PageHolder;
import com.github.mayangbo666.vo.AjaxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FlowController extends BaseController {

    @Autowired
    private FlowService flowService;

    @Autowired
    private CardService cardService;

    @ResponseBody
    @RequestMapping("/listFlow")
    public AjaxVO listFlow(HttpSession session, String cardNum, String password, String curPage, String pageNum) {
        try {
            int userId = getUserId(session);
            cardService.checkCard(userId, cardNum, password);
            PageHolder<FlowDTO> data = flowService.listFlow(cardNum, curPage, pageNum);
            return AjaxVO.success(data);
        } catch (MyException me) {
            return AjaxVO.failed(me.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxVO.failed("请联系客服");
        }
    }

    @RequestMapping("/downFlow")
    public void downFlow(HttpSession session, HttpServletResponse response, String cardNum, String password) {

        int userId = getUserId(session);
        cardService.checkCard(userId, cardNum, password);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = df.format(new Date());

        response.setContentType("application/octet-stream");// 二进制流
        response.addHeader("Content-Disposition", "attachment;filename=" + cardNum + " " + dateStr + " .csv");

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "utf8"))) {

            StringBuilder sx = new StringBuilder();
            sx.append("ID").append("卡号").append("金额").append("交易类型").append("交易时间");

            bw.write(sx.toString());
            bw.newLine();
            bw.flush();

            String pageNum = 10 + "";
            String curPage = 1 + "";

            while (true) {

                PageHolder<FlowDTO> flowDTOPageHolder = flowService.listFlow(cardNum, curPage, pageNum);
                List<FlowDTO> flowDTOList = flowDTOPageHolder.getData();

                for (FlowDTO flowDTO : flowDTOList) {
                    sx = new StringBuilder();
                    sx.append(flowDTO.getId()).append(flowDTO.getCardNum()).append(flowDTO.getTradeAmount())
                            .append(flowDTO.getTradeType()).append(flowDTO.getCreateTime());

                    bw.write(sx.toString());
                    bw.newLine();
                    bw.flush();
                }

                Integer tempNum = Integer.valueOf(curPage);
                ++tempNum;
                curPage = tempNum.toString();

                if (tempNum > flowDTOPageHolder.getTotalPage()) {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MyException("下载流水异常");
        }
    }
}
