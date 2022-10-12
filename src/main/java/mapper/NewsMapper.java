package mapper;

import lmc.model.News;

import java.util.List;

public interface NewsMapper {
    public List<News> getAllStickNews();
    public  List<News> getNewsByCategoryId(int categoryId);
    List<News> getNewsByTagId(int tagId);
    public News getNewsById(int id);
    List<News> getRecommendedNews(int newsId);
    List<News> getHotNews();
}
