package lk.ijse.bo.custom;

import lk.ijse.dto.AdminReportsDTO;
import lk.ijse.entity.AdminReports;

import java.sql.SQLException;

public interface AdminReportsBO {

    AdminReportsDTO getAllReports(String id) throws SQLException, ClassNotFoundException;

    boolean saveReports(AdminReportsDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateReports(AdminReportsDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteReports(String id) throws SQLException, ClassNotFoundException;

    AdminReportsDTO searchReports(String id) throws SQLException, ClassNotFoundException;


}
