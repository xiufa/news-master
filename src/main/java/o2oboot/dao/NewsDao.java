package o2oboot.dao;

import o2oboot.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsDao {
    int insertNews(News news);
    News queryNewsById(Long newsId);
    List<News> queryNewsList(News news,int rowIndex,int pageSize);
    int queryNewsCount(News news);
    int updateNews(News news);
    List<News> queryAllNews();
}
