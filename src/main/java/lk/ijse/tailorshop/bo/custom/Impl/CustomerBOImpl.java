package lk.ijse.tailorshop.bo.custom.Impl;

import lk.ijse.tailorshop.bo.custom.CustomerBO;
import lk.ijse.tailorshop.dao.DAOFactory;
import lk.ijse.tailorshop.dao.custom.CustomerDAO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();

        ArrayList<Customer> all = customerDAO.getAll();

        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getCustomerId(),c.getName(),c.getGender(),c.getAddress(),
                    c.getContactNumber(), c.getEmail()));
        }
        return allCustomers;
    }

    @Override
    public  boolean saveCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        // In here you can now save your customer

        return customerDAO.save(new Customer(dto.getCustomerId(),
                dto.getName(), dto.getGender(), dto.getAddress(), dto.getContactNumber(), dto.getEmail()));

    }

    @Override

    public  boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCustomerId(),
                dto.getName(), dto.getGender(), dto.getAddress(), dto.getContactNumber(), dto.getEmail()));

    }

    @Override
    public  CustomerDTO searchByIdCustomers(String customerId) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.searchById(customerId);
        return new CustomerDTO(c.getCustomerId(),c.getName(),c.getGender(),c.getAddress(),c.getContactNumber(),c.getEmail());
    }

    @Override
    public  boolean deleteCustomers(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId);
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {

        return customerDAO.getCount();
    }

}
