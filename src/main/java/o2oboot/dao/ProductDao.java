package o2oboot.dao;

import dlnu.o2oboot.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    public int insertProduct(Product product);
    public Product queryProductById(@Param("productId") Long productId);

    public int updateProduct(Product product);

    public List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    public int queryProductCount(@Param("productCondition") Product productCondition);

    public int updateProductCategoryToNull(Long productCategoryId);
}
