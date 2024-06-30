package lk.ijse.tailorshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

        private static lk.ijse.tailorshop.db.DbConnection dbConnection;
        private Connection connection;

        private DbConnection() throws SQLException {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tailorshop",
                    "root",
                    "Ijse@123"
            );
        }
        public static lk.ijse.tailorshop.db.DbConnection getInstance() throws SQLException {
            return (dbConnection == null) ? dbConnection = new lk.ijse.tailorshop.db.DbConnection() : dbConnection;
        }

        public Connection getConnection() {
            return connection;
        }
    }



