package o2oboot.web.user;

import o2oboot.entity.User;
import o2oboot.service.UserService;
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
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/checkUserId",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> checkUserId (HttpServletRequest request){
        String userID=request.getParameter("userID");

        int s = userService.checkUserID(userID);
        Map<String,Object> map = new HashMap<>();
        if(s<1){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        return map;
    }

    @RequestMapping(value = "/userSignUp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> userSignUp (HttpServletRequest request){
        String userID=request.getParameter("userID");
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        String gender=request.getParameter("gender");

        User user=new User(userID,userName,password,gender);
        int s=userService.addUser(user);
        Map<String,Object> map = new HashMap<>();
        if(s<1){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        return map;
    }

    @RequestMapping(value = "/userSignIn",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> userSignIn (HttpServletRequest request){
        String userID=request.getParameter("userID");
        String password=request.getParameter("password");

        int s=userService.checkUserSingIn(userID,password);
        Map<String,Object> map = new HashMap<>();
        if(s<1){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        return map;
    }

    @RequestMapping(value = "/userDetail",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> userDetail (HttpServletRequest request){
        String userID=request.getParameter("userID");

        User user=userService.getUserDetail(userID);
        Map<String,Object> map = new HashMap<>();
        map.put("userDetail",user);
        return map;
    }

    @RequestMapping(value = "/modifyUser",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> modifyUser (HttpServletRequest request){
        String userID=request.getParameter("userID");
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        String gender=request.getParameter("gender");

        User user=new User(userID,userName,password,gender);
        int s=userService.modifyUser(user);
        Map<String,Object> map = new HashMap<>();
        if(s<1){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        return map;
    }
}
