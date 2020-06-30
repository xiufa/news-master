package o2oboot.web.shopadmin;


import com.fasterxml.jackson.databind.ObjectMapper;

import o2oboot.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {

    private int MAX_FILE_COUNT=6;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/addproduct" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addProduct(@Param("productStr")String productStr, HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();



        if(request.getParameter("verifyCodeActual")==null || !request.getParameter("verifyCodeActual").equals(request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY))){
            model.put("success",false);
            model.put("errMsg","验证码错误");
            return model;
        }

        ObjectMapper objectMapper=new ObjectMapper();
        try {
            Product product=objectMapper.readValue(productStr,Product.class);
            CommonsMultipartFile productImg=null;
            CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
            List<ImageHolder> imageHolderList=new ArrayList<ImageHolder>();
            if(commonsMultipartResolver.isMultipart(request)){
                MultipartHttpServletRequest multipartHttpServletRequest
                        = (MultipartHttpServletRequest) request;
                productImg= (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg");

                for(int i=0;i<MAX_FILE_COUNT;i++){
                    CommonsMultipartFile temp=
                            (CommonsMultipartFile) multipartHttpServletRequest
                                    .getFile("productImg"+(i));
                    if(temp==null){
                        break;
                    }
                    imageHolderList.add(new ImageHolder(temp.getInputStream(),temp.getOriginalFilename()));
                }

                if(productImg==null){
                    model.put("success",false);
                    model.put("errMsg","缩略图不能为空");
                    return model;
                }

//


                Shop shop=(Shop) request.getSession().getAttribute("currentShop");

//                shop=new Shop();
//                shop.setShopId(68L);


                product.setShop(shop);
                product.setEnableStatus(0);

                ProductExecution pe=productService.addProduct(product,
                        new ImageHolder(productImg.getInputStream(),productImg.getOriginalFilename())
                        ,imageHolderList);

                if(pe.getState()== ProductStateEnum.SUCCESS.getState()){
                    model.put("success",true);
                    return model;
                }else{
                    model.put("success",false);
                    model.put("errMsg",pe.getStateInfo());
                    return model;
                }

            }else{
                model.put("success",false);
                model.put("errMsg","缩略图不能为空");
                return model;
            }

        } catch (IOException e) {
            e.printStackTrace();
            model.put("success",false);
            model.put("errMsg",e.toString());
            return model;
        }
    }


    @RequestMapping(value = "/modifyproduct",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyProduct(@Param("productStr")String productStr, HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();

        String statusChangeStr=request.getParameter("statusChange");
        boolean statusChange=Boolean.valueOf(statusChangeStr);

        if(!statusChange&&(request.getParameter("verifyCodeActual")==null || !request.getParameter("verifyCodeActual").equals(request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)))){
            model.put("success",false);
            model.put("errMsg","验证码错误");
            return model;
        }

        ObjectMapper objectMapper=new ObjectMapper();
        try {
            Product product=objectMapper.readValue(productStr,Product.class);
            CommonsMultipartFile productImg=null;
            CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
            List<ImageHolder> imageHolderList=new ArrayList<ImageHolder>();
            ImageHolder thum=null;
            if(commonsMultipartResolver.isMultipart(request)){
                MultipartHttpServletRequest multipartHttpServletRequest
                        = (MultipartHttpServletRequest) request;
                productImg= (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg");
                if(productImg!=null){
                    thum=new ImageHolder(productImg.getInputStream(),productImg.getOriginalFilename());
                }
                for(int i=0;i<MAX_FILE_COUNT;i++){
                    CommonsMultipartFile temp=
                            (CommonsMultipartFile) multipartHttpServletRequest
                                    .getFile("productImg"+(i));
                    if(temp==null){
                        break;
                    }
                    imageHolderList.add(new ImageHolder(temp.getInputStream(),temp.getOriginalFilename()));
                }


             }
//


                Shop shop=(Shop) request.getSession().getAttribute("currentShop");

//                shop=new Shop();
//                shop.setShopId(68L);


                product.setShop(shop);
//                product.setEnableStatus(0);

                ProductExecution pe=productService.modifyProduct(product,
                        thum
                        ,imageHolderList);

                if(pe.getState()== ProductStateEnum.SUCCESS.getState()){
                    model.put("success",true);
                    return model;
                }else{
                    model.put("success",false);
                    model.put("errMsg",pe.getStateInfo());
                    return model;
                }



        } catch (IOException e) {
            e.printStackTrace();
            model.put("success",false);
            model.put("errMsg",e.toString());
            return model;
        }
    }


    @RequestMapping(value = "/product",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getProductById(@Param("productId")Long productId, HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();
        Product product=productService.getProductById(productId);
        List<ProductCategory> productCategoryList=productCategoryService.getProductCategoryByshopId(product.getShop().getShopId());
        if(product!=null){
            model.put("product",product);
            model.put("productCategoryList",productCategoryList);
            model.put("success",true);
        }else{
            model.put("success", false);
            model.put("errMsg", "productId为空");
        }
        return model;
    }

    @RequestMapping(value="/getproductlist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getProductList(@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize,HttpServletRequest request){
        Map<String,Object> model=new HashMap<String, Object>();
        Shop shop= (Shop) request.getSession().getAttribute("currentShop");
        int rowIndex= PageCalculator.calculateRowIndex(pageIndex,pageSize);

//        shop=new Shop();
//        shop.setShopId(68L);

        if(shop!=null &&shop.getShopId()!=null ){

            String tempId=request.getParameter("productCategoryId");
            Long productCategoryId;
            if(tempId!=null)
               productCategoryId=Long.valueOf(tempId);
            else{
                productCategoryId=null;
            }
            String productName=request.getParameter("productName");

            Product product=compactProductCondition(shop,productCategoryId,productName);
            ProductExecution pe;
                pe=productService.getProductList(product,rowIndex,pageSize);


            List<Product> list=pe.getList();

            if(pe.getState()==ProductStateEnum.SUCCESS.getState()){
                model.put("success",true);
                model.put("productList",list);
                model.put("count",pe.getCount());
            }else{
                model.put("success",false);
                model.put("errMsg",pe.getStateInfo());
            }
        }else{
            model.put("success",false);
            model.put("errMsg","参数错误");
        }

        return model;
    }

    private Product compactProductCondition(Shop shop,Long productCategoryId,String productName){
        Product product=new Product();

        product.setShop(shop);

        if(productCategoryId!=null){
            ProductCategory productCategory=new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            product.setProductCategory(productCategory);
        }
        if(productName!=null){
            product.setProductName(productName);
        }
        return product;

    }

}
