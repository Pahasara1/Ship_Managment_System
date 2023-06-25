package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RegisterBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BuyersDAO;
import lk.ijse.dao.custom.RegisterDAO;
import lk.ijse.db.DBconnection;
import lk.ijse.dto.BuyersDTO;
import lk.ijse.dto.MachineDTO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.entity.Buyers;
import lk.ijse.entity.Machine;
import lk.ijse.entity.Register;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterBOimpl implements RegisterBO {
    RegisterDAO registerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Register);

    @Override
    public RegisterDTO getAllRegistration(String id) throws SQLException, ClassNotFoundException {
        Register register = registerDAO.getData(id);
        return new RegisterDTO(register.getAdminName(),
                register.getAdminPossision(),
                register.getAdminNic(),
                register.getEMail(),
                register.getPassword(),
                register.getConfirmPassword(),
                register.getContact(),
                register.getAdmin_Code()
        );
    }

    @Override
    public boolean saveRegistration(RegisterDTO dto) throws SQLException, ClassNotFoundException {
        return registerDAO.save(new Register(dto.getAdminName(), dto.getAdminPossision(), dto.getAdminNic(), dto.getEMail(), dto.getPassword(), dto.getConfirmPassword(), dto.getContact(), dto.getAdmin_Code()));
    }

    @Override
    public boolean deleteRegistration(String id) throws SQLException, ClassNotFoundException {
        return registerDAO.delete(id);
    }

    @Override
    public RegisterDTO searchRegistration(String id) throws SQLException, ClassNotFoundException {
        Register register = registerDAO.search(id);
        return new RegisterDTO(register.getAdminName(),
                register.getAdminPossision(),
                register.getAdminNic(),
                register.getEMail(),
                register.getPassword(),
                register.getConfirmPassword(),
                register.getContact(),
                register.getAdmin_Code()
        );
    }

    @Override
    public String getPassword() throws SQLException {
        String sql = "SELECT Password from admin";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        }
        return null;
    }

    @Override
    public String getEMail() throws SQLException {
        String sql = "SELECT EMail from admin";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        }
        return null;
    }
}
