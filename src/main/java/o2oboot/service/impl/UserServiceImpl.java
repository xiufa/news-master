package o2oboot.service.impl;

import o2oboot.dao.UserDao;
import o2oboot.entity.User;
import o2oboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int checkUserSingIn(String userID, String password) {
        return userDao.queryUserSignIn(userID,password);
    }

    @Override
    public int checkUserID(String userID) {
        return userDao.queryUserById(userID);
    }

    @Override
    public User getUserDetail(String userID) {
        return userDao.queryUser(userID);
    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);
    }
}
