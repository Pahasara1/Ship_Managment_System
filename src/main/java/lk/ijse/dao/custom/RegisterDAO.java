package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DBconnection;
import lk.ijse.entity.Register;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RegisterDAO extends CrudDAO<Register, String> {

    }
