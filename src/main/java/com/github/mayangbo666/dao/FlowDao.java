package com.github.mayangbo666.dao;

import com.github.mayangbo666.entity.Flow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowDao {

    int save(Flow flow);

    int countFlowByCardNum(String cardNum);

    List<Flow> listFlowByCardNum(@Param("cardNum") String cardNum, @Param("offset")int offset,@Param("pageNum") int pageNum);
}
