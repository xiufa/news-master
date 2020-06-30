package o2oboot.entity;

public class NewsCategory {
    private Long newsCategoryId;
    private String newsCategoryName;
    private Integer priority;
    private NewsCategory parent;

    public NewsCategory(Long newsCategoryId, String newsCategoryName, Integer priority, NewsCategory parent) {
        this.newsCategoryId = newsCategoryId;
        this.newsCategoryName = newsCategoryName;
        this.priority = priority;
        this.parent = parent;
    }

    public NewsCategory() {
    }

    public Long getNewsCategoryId() {
        return newsCategoryId;
    }

    public void setNewsCategoryId(Long newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    public String getNewsCategoryName() {
        return newsCategoryName;
    }

    public void setNewsCategoryName(String newsCategoryName) {
        this.newsCategoryName = newsCategoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public NewsCategory getParent() {
        return parent;
    }

    public void setParent(NewsCategory parent) {
        this.parent = parent;
    }
}
