package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DailyReportsBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BuyersDAO;
import lk.ijse.dao.custom.DailyReportsDAO;
import lk.ijse.dto.BuyersDTO;
import lk.ijse.dto.DailyReportsDTO;
import lk.ijse.entity.Buyers;
import lk.ijse.entity.DailyReports;

import java.sql.SQLException;

public class DailyreportsBOimpl implements DailyReportsBO {

    DailyReportsDAO dailyReportsDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.dailyReports);

    @Override
    public DailyReportsDTO getAllReports(String id) throws SQLException, ClassNotFoundException {
        DailyReports dailyReports = dailyReportsDAO.getData(id);
        return new DailyReportsDTO(dailyReports.getReportId(),
                dailyReports.getEmployeeNic(),
                dailyReports.getTemperatureLevel(),
                dailyReports.getMachineRunning()
        );
    }

    @Override
    public boolean saveReports(DailyReportsDTO dto) throws SQLException, ClassNotFoundException {
        return dailyReportsDAO.save( new DailyReports(dto.getReportId(),dto.getEmployeeNic(),dto.getTemperatureLevel(),dto.getMachineRunning()));
    }

    @Override
    public boolean updateReports(DailyReportsDTO dto) throws SQLException, ClassNotFoundException {
        return dailyReportsDAO.update( new DailyReports(dto.getReportId(),dto.getEmployeeNic(),dto.getTemperatureLevel(),dto.getMachineRunning()));
    }

    @Override
    public boolean deleteReports(String id) throws SQLException, ClassNotFoundException {
        return dailyReportsDAO.delete(id);
    }

    @Override
    public DailyReportsDTO searchReports(String id) throws SQLException, ClassNotFoundException {
        DailyReports dl = dailyReportsDAO.search(id);
        return new DailyReportsDTO(dl.getReportId(),
                dl.getEmployeeNic(),
                dl.getTemperatureLevel(),
                dl.getMachineRunning());
    }
}
