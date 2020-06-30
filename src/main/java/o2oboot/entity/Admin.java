package o2oboot.entity;

public class Admin {
    private String adminId;
    private String adminPassword;
    private String adminName;
    private String gender;

    public Admin() {
    }

    public Admin(String adminID, String adminPassword, String adminName, String gender) {
        this.adminId = adminID;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.gender = gender;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
