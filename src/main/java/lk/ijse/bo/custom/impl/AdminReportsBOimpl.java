package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AdminReportsBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminReportsDAO;
import lk.ijse.dto.AdminReportsDTO;
import lk.ijse.entity.AdminReports;

import java.sql.SQLException;

public class AdminReportsBOimpl implements AdminReportsBO {

    AdminReportsDAO adminReportsDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.AdminReports);

    @Override
    public AdminReportsDTO getAllReports(String id) throws SQLException, ClassNotFoundException {
        AdminReports adminReports = adminReportsDAO.getData(id);
        return new AdminReportsDTO(
                adminReports.getReportId(),
                adminReports.getAdminNic(),
                adminReports.getEmployeeNic(),
                adminReports.getTime(),
                adminReports.getDate());
    }

    @Override
    public boolean saveReports(AdminReportsDTO dto) throws SQLException, ClassNotFoundException {
        return adminReportsDAO.save( new AdminReports(dto.getReportId(),dto.getAdminNic(),dto.getEmployeeNic(),dto.getTime(),dto.getDate()));
    }

    @Override
    public boolean updateReports(AdminReportsDTO dto) throws SQLException, ClassNotFoundException {
        return adminReportsDAO.update( new AdminReports(dto.getReportId(),dto.getAdminNic(),dto.getEmployeeNic(),dto.getTime(),dto.getDate()));
    }

    @Override
    public boolean deleteReports(String id) throws SQLException, ClassNotFoundException {
        return adminReportsDAO.delete(id);
    }

    @Override
    public AdminReportsDTO searchReports(String id) throws SQLException, ClassNotFoundException {
        AdminReports um = adminReportsDAO.search(id);
        return new AdminReportsDTO(um.getReportId(),um.getAdminNic(),um.getEmployeeNic(),um.getTime(),um.getDate());
    }
}
