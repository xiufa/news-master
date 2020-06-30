package o2oboot.dao;

import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    int queryAdminSignIn(@Param("adminID") String adminID, @Param("adminPassword") String adminPassword);
}
