package o2oboot.entity;

import java.util.Date;
import java.util.List;

public class Product {

    private Long productId;
    private String productName;
    private String productDesc;

    private String imgAddr;

    private String normalPrice;
    private String promotionPrice;

    private Integer priority;
    private Integer enableStatus;

    private Date createTime;
    private Date lastEditTime;

    private int point;

    private List<ProductImg> productImgs;
    private ProductCategory productCategory;
    private Shop shop;


    public Product() {
    }


    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public void setProductImgs(List<ProductImg> productImgs) {
        this.productImgs = productImgs;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public List<ProductImg> getProductImgs() {
        return productImgs;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public Shop getShop() {
        return shop;
    }
}
