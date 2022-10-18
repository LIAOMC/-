package test.dao;

public class UserDaoSqlImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("你好，Sql！");
    }
}
