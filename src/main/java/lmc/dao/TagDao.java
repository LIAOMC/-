package lmc.dao;


import lmc.model.Tag;
import lmc.util.SqlSessionUtils;
import mapper.TagMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Set;

public class TagDao {
    public static List<Tag> getAllTags(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        TagMapper tagMapper = sqlSession.getMapper(TagMapper.class);
        List<Tag> tagList =tagMapper.getAllTags();
        return tagList;
    }
    public Set<Tag> getTagByNewsId(int newsId){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        TagMapper tagMapper = sqlSession.getMapper(TagMapper.class);
        Set<Tag> tagList =tagMapper.getTagByNewsId(newsId);
        return tagList;
    }
}
