package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.AdminReportsDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.entity.AdminReports;
import lk.ijse.db.DBconnection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AdminReportsDAOimpl implements AdminReportsDAO {

    @Override
    public AdminReports getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM adminreports WHERE Report_Id = ?",id);
        AdminReports adminreports = new AdminReports();
        while (set.next()) {
            adminreports.setReportId(set.getString(1));
            adminreports.setAdminNic(set.getString(2));
            adminreports.setEmployeeNic(set.getString(3));
            adminreports.setTime(set.getString(4));
            adminreports.setDate(set.getString(5));
        }
        return adminreports;
    }

    @Override
    public boolean save(AdminReports dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO adminreports(Report_Id,Admin_Nic,Employee_Nic,Time,Date) " +
                        "VALUES(?, ?, ?, ?, ?)",
                dto.getReportId(),
                dto.getAdminNic(),
                dto.getEmployeeNic(),
                dto.getTime(),
                dto.getDate());
    }

    @Override
    public boolean update(AdminReports dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE adminreports SET  Admin_Nic= ? ,Employee_Nic= ?, Time = ?, Date = ? WHERE Report_Id = ?",
                dto.getAdminNic(),
                dto.getEmployeeNic(),
                dto.getTime(),
                dto.getDate(),
                dto.getReportId());
    }

    @Override
    public boolean delete(String ReportId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM adminreports WHERE Report_Id=?";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, ReportId);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public AdminReports search(String reportId) throws SQLException, ClassNotFoundException {

        ResultSet set = SQLUtill.execute("SELECT * FROM adminreports WHERE Report_Id = ?", reportId);
        AdminReports adminReports= new AdminReports();
            if(set.next()) {

                adminReports.setReportId(set.getString(1));
                adminReports.setAdminNic(set.getString(2));
                adminReports.setEmployeeNic(set.getString(3));
                adminReports.setDate(set.getString(4));
                adminReports.setTime(set.getString(5));

            }
            return adminReports;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Report_Id FROM adminreports ORDER BY LENGTH(Report_Id),Report_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
}
