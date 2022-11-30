package test.ceshi;

import lmc.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.service.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
    //        UserService userService = new UserServiceImpl();
    //        ((UserServiceImpl)userService).setUserDao(new UserDaoSqlImpl());
    //        userService.getUser();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/beans.xml");
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");
        userServiceImpl.getUser();
        Class userClass = User.class;
    }
}
