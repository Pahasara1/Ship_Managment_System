package lk.ijse.bo.custom;

import lk.ijse.dto.BuyersDTO;
import lk.ijse.dto.DailyReportsDTO;

import java.sql.SQLException;

public interface DailyReportsBO {

    DailyReportsDTO getAllReports(String id) throws SQLException, ClassNotFoundException;

    boolean saveReports(DailyReportsDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateReports(DailyReportsDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteReports(String id) throws SQLException, ClassNotFoundException;

    DailyReportsDTO searchReports(String id) throws SQLException, ClassNotFoundException;

}
