package o2oboot.service.impl;

import o2oboot.dao.AdminDao;
import o2oboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public int checkadminSingIn(String adminID, String adminPassword) {
        return adminDao.queryAdminSignIn(adminID,adminPassword);
    }
}
