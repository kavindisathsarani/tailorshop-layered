package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.CustomerDAO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.entity.MaterialDetail;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public  ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");

        while (rst.next()) {
            Customer customer = new Customer(rst.getString("customerId"), rst.getString("name")
                    ,rst.getString("gender"), rst.getString("address"),
                    rst.getInt("contactNumber"),rst.getString("email"));

            allCustomers.add(customer);
        }
        return allCustomers;
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
    public  Customer searchById(String customerId) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE customerId=?", customerId + "");
        rst.next();
        return new Customer(customerId + "", rst.getString("name"),rst.getString("gender"),
                rst.getString("address"),rst.getInt("contactNumber"),rst.getString("email"));
    }

    @Override
    public  boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE customerId=?", customerId);
    }

    @Override
    public ArrayList<String> currentId() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateQty(ArrayList<MaterialDetail> mdList) {
        return false;
    }

    @Override
    public boolean save(ArrayList<MaterialDetail> mdList) {
        return false;
    }
}
