package com.github.mayangbo666.dao;

import com.github.mayangbo666.entity.Card;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardDao {

    int insertCardByCardNum(@Param("userId") int userId,
                            @Param("cardNum") String cardNum,
                            @Param("password") String password);

    int updateAmountByCardNum(@Param("cardNum") String cardNum,
                                   @Param("password") String password,
                                   @Param("amount") String newAmount);

    List<Card> selectCardByCardNum(String cardNum);



    List<Card> selectCardsByUserId(int userId);

//    int selectUserIdByCardNum(String cardNum);


}
