package lk.ijse.bo.custom;

import lk.ijse.dto.BuyersDTO;
import lk.ijse.dto.MachineDTO;
import lk.ijse.dto.RegisterDTO;

import java.sql.SQLException;

public interface RegisterBO {

    RegisterDTO getAllRegistration(String id) throws SQLException, ClassNotFoundException;

    boolean saveRegistration(RegisterDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteRegistration(String id) throws SQLException, ClassNotFoundException;

    RegisterDTO searchRegistration(String id) throws SQLException, ClassNotFoundException;

    public  String getPassword() throws SQLException;

    public  String getEMail() throws SQLException;

}
