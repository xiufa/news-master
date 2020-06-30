package o2oboot.service.impl;

import o2oboot.dao.NewsCategoryDao;
import o2oboot.dto.NewsCategoryExecution;
import o2oboot.entity.NewsCategory;
import o2oboot.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsCategoryServiceImpl implements NewsCategoryService {
    @Autowired
    private NewsCategoryDao newsCategoryDao;

    @Override
    public int addNewsCategory(NewsCategory newsCategory) {
        return newsCategoryDao.insertNewsCategory(newsCategory);
    }

    @Override
    public NewsCategoryExecution getAllFatherNewsCategory() {
        return queryNewsCategoryByParent(null);
    }

    @Override
    public NewsCategoryExecution deleteNewsCategoryById(Long newsCategoryId) {
        int n=newsCategoryDao.deleteNewsCategory(newsCategoryId);
        NewsCategoryExecution newsCategoryExecution=new NewsCategoryExecution();

        if(n<1){
            newsCategoryExecution.setState(0);
            newsCategoryExecution.setStateInfo("fail");
        }else{
            newsCategoryExecution.setState(1);
            newsCategoryExecution.setStateInfo("success");
        }
        return newsCategoryExecution;
    }

    @Override
    public NewsCategoryExecution queryNewsCategoryByParent(NewsCategory newsCategory) {
        List<NewsCategory> list = newsCategoryDao.queryNewsCategoryByParent(newsCategory);
        NewsCategoryExecution newsCategoryExecution=new NewsCategoryExecution();

        if(list!=null){
            newsCategoryExecution.setNewsList(list);
            newsCategoryExecution.setCount(list.size());
            newsCategoryExecution.setState(1);
            newsCategoryExecution.setStateInfo("success");
        }else{
            newsCategoryExecution.setState(0);
            newsCategoryExecution.setStateInfo("fail");
        }

        return  newsCategoryExecution;
    }
}
