package o2oboot.service.impl;


import o2oboot.dao.ProductDao;
import o2oboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;


    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imgHolderList) {
       try{

           if(product==null ||product.getShop()==null || product.getShop().getShopId()==null){
               return new ProductExecution(ProductStateEnum.EMPTY);
           }

//TODO 11/1

           if(thumbnail!=null) {
               addProductThumbnail(product, thumbnail);
           }

           int n=productDao.insertProduct(product);

           if(n<=0){

               return new ProductExecution(ProductStateEnum.INNER_ERROR);
           }else{

               if(imgHolderList!=null &&imgHolderList.size()>0){
                   addProductImg(product,imgHolderList);

                   productImgDao.batchInsertProductImg(product.getProductImgs());

               }
               return new ProductExecution(ProductStateEnum.SUCCESS,product);
           }







       }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

       }

    }

    private void addProductThumbnail(Product product,ImageHolder imageHolder){
        String dest= PathUtil.getShopImagePath(product.getShop().getShopId());
        String res= ImageUtil.generateThumbnail(imageHolder.getImage(),imageHolder.getImageName(),dest);
        product.setImgAddr(res);
    }

    private void addProductImg(Product product,List<ImageHolder> imgHolderList) {
        String dest;
        String res;
        List<ProductImg> list = new ArrayList<ProductImg>();
        for (int i = 0; i < imgHolderList.size(); i++) {
            dest = PathUtil.getShopImagePath(product.getShop().getShopId());
            res = ImageUtil.generateThumbnail(imgHolderList.get(i).getImage(), imgHolderList.get(i).getImageName(), dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(res);
            productImg.setCreateTime(new Date());
            productImg.setProductId(product.getProductId());
            list.add(productImg);
        }
        product.setProductImgs(list);
    }

    public Product getProductById(Long productId){
        return productDao.queryProductById(productId);
    }

    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imgHolderList){
        if(product!=null && product.getProductId()!=null){
            product.setLastEditTime(new Date());
            if(thumbnail!=null){
                Product temp=productDao.queryProductById(product.getProductId());
                if(temp.getImgAddr()!=null){
                    ImageUtil.deleteFileOrPath(temp.getImgAddr());
                }
                addProductThumbnail(product,thumbnail);


            }
            if(imgHolderList!=null&&imgHolderList.size()>0){
                List<ProductImg> list=productImgDao.queryProductImgListByProductId(product.getProductId());
                for( ProductImg img: list){
                    if(img.getImgAddr()!=null)
                        ImageUtil.deleteFileOrPath(img.getImgAddr());
                }
                productImgDao.deleteProductImgByProductId(product.getProductId());
                addProductImg(product,imgHolderList);

                productImgDao.batchInsertProductImg(product.getProductImgs());
            }

            int n=productDao.updateProduct(product);
            if(n<=0){
                return new ProductExecution(ProductStateEnum.INNER_ERROR);
            }else {
                return new ProductExecution(ProductStateEnum.SUCCESS,product);
            }
        }else{
            return new ProductExecution(ProductStateEnum.EMPTY);
        }

    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        List<Product> list=productDao.queryProductList(productCondition, PageCalculator.calculateRowIndex(pageIndex,pageSize),pageSize);
        int count=productDao.queryProductCount(productCondition);
        if(list!=null && list.size()>0){
            ProductExecution pe=new ProductExecution(ProductStateEnum.SUCCESS,list);
            pe.setCount(count);
            return pe;
        }else{
            return new ProductExecution(ProductStateEnum.INNER_ERROR);
        }

    }

}
