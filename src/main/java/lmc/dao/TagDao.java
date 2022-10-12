package lmc.dao;


import lmc.model.Tag;
import lmc.util.SqlSessionUtils;
import mapper.TagMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TagDao {
    public static List<Tag> getAllTags(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        TagMapper tagMapper = sqlSession.getMapper(TagMapper.class);
        List<Tag> tagList =tagMapper.getAllTags();
        return tagList;
    }
}
