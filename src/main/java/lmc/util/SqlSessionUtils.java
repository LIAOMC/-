package lmc.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class  SqlSessionUtils {
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        //加载核心配置文件
        InputStream is;
        {
            try {
                is = Resources.getResourceAsStream("mybatis-config.xml");
                //获取SqlSessionFactoryBuilder对象
                SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
                //获取sqlSessionFactory
                SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
                //获取SqlSession
                sqlSession = sqlSessionFactory.openSession(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return sqlSession;
    }
}
