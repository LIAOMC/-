package lmc.dao;

import lmc.model.News;
import lmc.model.User;
import lmc.util.Md5Util;
import lmc.util.SqlSessionUtils;
import mapper.NewsMapper;
import mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

public class UserDao {
    public  User getUser(String account , String password){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user =userMapper.getUser(account, Md5Util.md5(password));
        return user;
    }
    public Boolean isUserExited(String key,String value){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user =userMapper.isUserExited(key,value);
        if(user!=null){
            return true;
        }else{
            return false;
        }

    }
    public  Boolean addUser(User user){
        Map<String,Object> map=new HashMap<>();
        map.put("account",user.getAccount());
        map.put("password", user.getPassword());
        map.put("nickname",user.getNickname());
        map.put("photo",user.getPhoto());
        map.put("birthday",new Date(user.getBirthday().getTime()));
        map.put("email",user.getEmail());
        map.put("mobile",user.getMobile());
        map.put("regDate",new Timestamp(new java.util.Date().getTime()));
        map.put("forbidden",false);
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Boolean aBoolean = userMapper.addUser(map);
        return aBoolean;
    }
    public void updateUser(User user) {
        String nickname=user.getNickname();
        String photo=user.getPhoto();
        Date birthday=new java.sql.Date(user.getBirthday().getTime());
        String email=user.getEmail();
        String mobile=user.getMobile();
        String account=user.getAccount();
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateUser(nickname,photo,birthday,email,mobile,account);
    }
    public void changeUserPassword(String account,String password){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.changeUserPassword(account,password);
    }
}
