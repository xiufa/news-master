package o2oboot.web.admin;

import o2oboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/adminSignIn",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> adminSignIn (HttpServletRequest request){
        String adminID=request.getParameter("userID");
        String adminPassword=request.getParameter("password");

        int s=adminService.checkadminSingIn(adminID,adminPassword);
        Map<String,Object> map = new HashMap<>();
        if(s==1){
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        return map;
    }
}
