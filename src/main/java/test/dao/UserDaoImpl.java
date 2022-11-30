package test.dao;

import org.springframework.stereotype.Component;


public class UserDaoImpl implements UserDao{//实现类
    @Override
    public void getUser() {
        System.out.println("你好，Dao！");
    }
}
