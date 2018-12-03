package com.github.mayangbo666.dao;

import com.github.mayangbo666.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

   User getUserByLoginCode(String loginCode);

   Integer insertUser(@Param("loginCode")String loginCode, @Param("password") String password, @Param("userName")String userName);
}
