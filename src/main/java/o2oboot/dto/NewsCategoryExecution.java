package o2oboot.dto;

import o2oboot.entity.News;
import o2oboot.entity.NewsCategory;

import java.util.List;

public class NewsCategoryExecution {
    private NewsCategory newsCategory;
    private List<NewsCategory> newsList;
    private int count;

    private int state;
    private String stateInfo;

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public List<NewsCategory> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsCategory> newsList) {
        this.newsList = newsList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
