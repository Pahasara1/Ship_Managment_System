package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.DailyReportsDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.entity.DailyReports;
import lk.ijse.db.DBconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DailyReportsDAOimpl implements DailyReportsDAO {
    @Override
    public DailyReports getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM daily_reports WHERE Report_Id = ?",id);
        DailyReports dailyReports = new DailyReports();
        while (set.next()) {
            dailyReports.setReportId(set.getString(1));
            dailyReports.setEmployeeNic(set.getString(2));
            dailyReports.setTemperatureLevel(set.getString(3));
            dailyReports.setMachineRunning(set.getString(4));
        }
        return dailyReports;
    }

    @Override
    public boolean save(DailyReports dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO daily_reports(Report_Id,Employee_Nic,Temperature_Level,Machine_Running_Pressure) " +
                "VALUES(?, ?, ?, ?)",
                dto.getReportId(),
                dto.getEmployeeNic(),
                dto.getTemperatureLevel(),
                dto.getMachineRunning());
    }

    @Override
    public boolean update(DailyReports dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE daily_reports SET  Employee_Nic= ? ,Temperature_Level= ?,Machine_Running_Pressure = ? WHERE Report_Id = ?",
                dto.getEmployeeNic(),
                dto.getTemperatureLevel(),
                dto.getMachineRunning(),
                dto.getReportId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM daily_reports WHERE Report_Id=?";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, s);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public DailyReports search(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM daily_reports WHERE Report_Id = ?", s);
        DailyReports dailyReports= new DailyReports();
        if(set.next()) {

            dailyReports.setReportId(set.getString(1));
            dailyReports.setEmployeeNic(set.getString(2));
            dailyReports.setTemperatureLevel(set.getString(3));
            dailyReports.setMachineRunning(set.getString(4));

        }
        return dailyReports;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Report_Id FROM daily_reports ORDER BY LENGTH(Report_Id),Report_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
}
