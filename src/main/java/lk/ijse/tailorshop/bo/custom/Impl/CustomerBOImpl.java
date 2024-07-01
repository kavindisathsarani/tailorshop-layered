package lk.ijse.tailorshop.bo.custom.Impl;

import lk.ijse.tailorshop.bo.custom.CustomerBO;
import lk.ijse.tailorshop.dao.DAOFactory;
import lk.ijse.tailorshop.dao.custom.CustomerDAO;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.SQLException;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException {

        return null;
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
    public  Customer searchByIdCustomers(String customerId) throws SQLException {


        return null;
    }

    @Override
    public  boolean deleteCustomers(String customerId) throws SQLException {

        return false;
    }

}
