package test.dao;

public class UserDaoImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("你好，Dao！");
    }
}
