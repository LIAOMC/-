package lmc.dao;

import lmc.model.News;
import lmc.util.SqlSessionUtils;
import mapper.NewsMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class NewsDao {
    public static List<News> getAllStickNews(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
        List<News> newsList =newsMapper.getAllStickNews();
        return newsList;
    }
    public static List<News> getNewsByCategoryId(@Param("categoryId") int categoryId){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
        List<News> newsList =newsMapper.getNewsByCategoryId(categoryId);
        return newsList;
    }
    public static List<News> getNewsByTagId(@Param("tagId") int tagId){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
        List<News> newsList =newsMapper.getNewsByTagId(tagId);
        return newsList;
    }
    public static News getNewsById(@Param("id") int id){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
        News news =newsMapper.getNewsById(id);
        return news;
    }
    public static List<News> getRecommendedNews(@Param("id") int id){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
        List<News> newsList =newsMapper.getRecommendedNews(id);
        return newsList;
    }
    public static List<News> getHotNews(){
        SqlSession sqlSession= SqlSessionUtils.getSqlSession();
        NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
        List<News> newsList =newsMapper.getHotNews();
        return newsList;
    }
}
