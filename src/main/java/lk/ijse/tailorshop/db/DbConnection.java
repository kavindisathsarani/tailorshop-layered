package lk.ijse.tailorshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

   /* private static DbConnection dbConnection;
    private final Connection connection;

    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Ijse@123");
    }

    public static DbConnection getDbConnection() throws SQLException, ClassNotFoundException {
        return dbConnection == null ? dbConnection= new DbConnection() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }*/

    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tailorshop",
                "root",
                "Ijse@123"
        );
    }
    public static DbConnection getDbConnection() throws SQLException {
        return (dbConnection == null) ? dbConnection = new DbConnection() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
    }



