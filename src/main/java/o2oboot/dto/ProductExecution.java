package o2oboot.dto;

import dlnu.o2oboot.entity.Product;
import dlnu.o2oboot.enums.ProductStateEnum;

import java.util.List;

public class ProductExecution {

    private Product product;
    private List<Product> list;

    private int count;

    private int state;
    private String stateInfo;


    public  ProductExecution(ProductStateEnum pe){

        state=pe.getState();
        stateInfo=pe.getStateInfo();

    }


    public  ProductExecution(ProductStateEnum pe,Product product){

        state=pe.getState();
        stateInfo=pe.getStateInfo();
        this.product=product;
    }

    public  ProductExecution(ProductStateEnum pe,List<Product> list){

        state=pe.getState();
        stateInfo=pe.getStateInfo();
        this.list=list;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Product getProduct() {
        return product;
    }

    public List<Product> getList() {
        return list;
    }

    public int getCount() {
        return count;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
