package lk.ijse.db;

/*
    @author DanujaV
    @created 3/14/23 - 4:46 PM
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static DBconnection dbConnection;
    private Connection con;

    private DBconnection() throws SQLException {
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ship",
                "root",
                "1234"
        );
    }

    public static DBconnection getInstance() throws SQLException {
        /*if(dbConnection == null) {
            dbConnection = new DBConnection();
            return dbConnection;
        } else {
            return dbConnection;
        }*/
        return (null == dbConnection) ? dbConnection = new DBconnection()
                : dbConnection;
    }
    public Connection getConnection() {
        return con;
    }
}
