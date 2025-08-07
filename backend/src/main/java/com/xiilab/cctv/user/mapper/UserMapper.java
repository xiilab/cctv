package com.xiilab.cctv.user.mapper;

import com.xiilab.cctv.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAllUsers();
    
    User selectUserById(@Param("id") Long id);
    
    User selectUserByUsername(@Param("username") String username);
    
    List<User> selectUsersByStatus(@Param("status") String status);
    
    int insertUser(User user);
    
    int updateUser(User user);
    
    int deleteUser(@Param("id") Long id);
    
    List<User> selectUsersByKeyword(@Param("keyword") String keyword);
}
