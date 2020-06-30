package o2oboot.dao;

import o2oboot.entity.NewsCategory;

import java.util.List;

public interface NewsCategoryDao {
    int insertNewsCategory(NewsCategory newsCategory);
    int deleteNewsCategory(Long newsCategoryId);
    List<NewsCategory> queryNewsCategoryByParent(NewsCategory newsCategory);


}
