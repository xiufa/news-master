package o2oboot.service;

import o2oboot.dto.NewsCategoryExecution;
import o2oboot.dto.NewsExecution;
import o2oboot.entity.News;
import o2oboot.entity.NewsCategory;

import java.util.List;

public interface NewsCategoryService {
    int addNewsCategory(NewsCategory newsCategory);

    NewsCategoryExecution getAllFatherNewsCategory();
    NewsCategoryExecution deleteNewsCategoryById(Long newsCategoryId);

    NewsCategoryExecution queryNewsCategoryByParent(NewsCategory newsCategory);





}
