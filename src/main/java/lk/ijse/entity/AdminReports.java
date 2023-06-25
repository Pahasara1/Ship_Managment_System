package lk.ijse.entity;

import lk.ijse.dto.AdminReportsDTO;

public class AdminReports extends AdminReportsDTO {
    private String ReportId;
    private String AdminNic;
    private String EmployeeNic;
    private String Time;
    private String Date;

    public AdminReports() {

    }

    public AdminReports(String reportId, String adminNic, String employeeNic, String time, String date) {
        ReportId = reportId;
        AdminNic = adminNic;
        EmployeeNic = employeeNic;
        Time = time;
        Date = date;
    }

    public String getReportId() {
        return ReportId;
    }

    public void setReportId(String reportId) {
        ReportId = reportId;
    }

    public String getAdminNic() {
        return AdminNic;
    }

    public void setAdminNic(String adminNic) {
        AdminNic = adminNic;
    }

    public String getEmployeeNic() {
        return EmployeeNic;
    }

    public void setEmployeeNic(String employeeNic) {
        EmployeeNic = employeeNic;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "AdminReports{" +
                "ReportId='" + ReportId + '\'' +
                ", AdminNic='" + AdminNic + '\'' +
                ", EmployeeNic='" + EmployeeNic + '\'' +
                ", Time='" + Time + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
