package test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import test.dao.UserDao;

import javax.annotation.Resource;

@Component
public class UserServiceImpl implements UserService{//业务实现类
//    @Autowired
//    @Qualifier("daoImpl")
    private UserDao userDao;
    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "userDao=" + userDao +
                '}';
    }
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public void getUser() {
        userDao.getUser();
    }
}
