package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.RegisterDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.db.DBconnection;
import lk.ijse.entity.Employees;
import lk.ijse.entity.Machine;
import lk.ijse.entity.Register;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDAOimpl implements RegisterDAO {
    @Override
    public Register getData(String nic) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM admin WHERE Admin_Nic = ?",nic);
        Register register = new Register();
        if (set.next()) {
            register.setAdminName(set.getString(1));
            register.setAdminPossision(set.getString(2));
            register.setAdminNic(set.getString(3));
            register.setEMail(set.getString(4));
            register.setPassword(set.getString(5));
            register.setConfirmPassword(set.getString(6));
            register.setContact(set.getString(7));
            register.setAdmin_Code(set.getString(8));

        }
        return register;
    }

    @Override
    public boolean save(Register dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO admin(Admin_Name,Admin_Possision,Admin_Nic,EMail,Password,Confirm_Password,Contact,Admin_Code) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                dto.getAdminName(),
                dto.getAdminPossision(),
                dto.getAdminNic(),
                dto.getEMail(),
                dto.getPassword(),
                dto.getConfirmPassword(),
                dto.getContact(),
                dto.getAdmin_Code());
    }

    @Override
    public boolean update(Register dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM admin WHERE Admin_Nic=?";
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
    public Register search(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM admin WHERE Admin_Nic = ?",s);
        Register register= new Register();
        if(set.next()) {

            register.setAdminName(set.getString(1));
            register.setAdminPossision(set.getString(2));
            register.setAdminNic(set.getString(3));
            register.setEMail(set.getString(4));
            register.setPassword(set.getString(5));
            register.setConfirmPassword(set.getString(6));
            register.setContact(set.getString(7));
            register.setAdmin_Code(set.getString(8));

        }
        return register;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Admin_Nic FROM admin ORDER BY LENGTH(Admin_Nic),Admin_Nic");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
}
