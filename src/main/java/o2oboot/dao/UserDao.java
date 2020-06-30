package o2oboot.dao;

import o2oboot.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int insertUser(User user);
    int queryUserSignIn(@Param("userID") String userID, @Param("password")String password);
    int queryUserById(@Param("userID") String userID);
    User queryUser(@Param("userID") String userID);
    int updateUser(User user);
}
