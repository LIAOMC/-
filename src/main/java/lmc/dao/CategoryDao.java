package lmc.dao;

import lmc.model.Category;
import lmc.util.SqlSessionUtils;
import mapper.CategoryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryDao {
    public  List<Category> getAllCategories(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
        List<Category> categoryList =categoryMapper.getAllCategories();
        return categoryList;
    }
}
