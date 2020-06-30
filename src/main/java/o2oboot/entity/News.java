package o2oboot.entity;

import java.util.Date;

public class News {
    private Long newsId;
    private String newsName;
    private NewsCategory newsCategory;
    private Integer views;
    private Integer priority;
    private Date createTime;
    public News() {
    }

    public News(Long newsId, String newsName, NewsCategory newsCategory, Integer views, Integer priority) {
        this.newsId = newsId;
        this.newsName = newsName;
        this.newsCategory = newsCategory;
        this.views = views;
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
