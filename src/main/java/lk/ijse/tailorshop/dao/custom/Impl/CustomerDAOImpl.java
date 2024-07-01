package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.CustomerDAO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public  List<Customer> getAll() throws SQLException {

        return null;
    }

    @Override
    public  boolean save(Customer entity) throws SQLException, ClassNotFoundException {

        // In here you can now save your customer


        return SQLUtil.execute("INSERT INTO customer(customerId,name,gender,address,contactNumber,email) VALUES(?,?,?,?,?,?)", entity.getCustomerId(),
                entity.getName(),entity.getGender(), entity.getAddress(),entity.getContactNumber(),
                entity.getEmail());
    }

    @Override

    public  boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name = ?, gender = ?, address = ?, contactNumber = ?, email = ? WHERE customerId = ?", entity.getName(),
                entity.getGender(), entity.getAddress(),entity.getContactNumber(),
                entity.getEmail(),entity.getCustomerId());
    }

    @Override
    public  Customer searchById(String customerId) throws SQLException {


        return null;
    }

    @Override
    public  boolean delete(String customerId) throws SQLException {

        return false;
    }
}
