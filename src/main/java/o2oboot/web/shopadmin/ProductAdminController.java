package o2oboot.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopadmin")
public class ProductAdminController {

    @RequestMapping("/productoperation")
    public String productOperation(){
        return "product/productoperation";
    }


    @RequestMapping("/productlist")
    public String productList(){
        return "product/productlist";
    }

}
