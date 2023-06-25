package lk.ijse.entity;

public class Register {
    private String AdminName;
    private String AdminPossision;
    private String AdminNic;
    private String EMail;
    private String Password;
    private String ConfirmPassword;
    private String Contact;
    private String Admin_Code;

    public Register() {
    }

    public Register(String adminName, String adminPossision, String adminNic, String EMail, String password, String confirmPassword, String contact, String admin_Code) {
        AdminName = adminName;
        AdminPossision = adminPossision;
        AdminNic = adminNic;
        this.EMail = EMail;
        Password = password;
        ConfirmPassword = confirmPassword;
        Contact = contact;
        Admin_Code = admin_Code;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getAdminPossision() {
        return AdminPossision;
    }

    public void setAdminPossision(String adminPossision) {
        AdminPossision = adminPossision;
    }

    public String getAdminNic() {
        return AdminNic;
    }

    public void setAdminNic(String adminNic) {
        AdminNic = adminNic;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAdmin_Code() {
        return Admin_Code;
    }

    public void setAdmin_Code(String admin_Code) {
        Admin_Code = admin_Code;
    }

    @Override
    public String toString() {
        return "RegisterTM{" +
                "AdminName='" + AdminName + '\'' +
                ", AdminPossision='" + AdminPossision + '\'' +
                ", AdminNic='" + AdminNic + '\'' +
                ", EMail='" + EMail + '\'' +
                ", Password='" + Password + '\'' +
                ", ConfirmPassword='" + ConfirmPassword + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Admin_Code='" + Admin_Code + '\'' +
                '}';
    }
}
