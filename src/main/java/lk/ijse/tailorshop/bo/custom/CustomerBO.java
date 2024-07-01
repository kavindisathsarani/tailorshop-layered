package lk.ijse.tailorshop.bo.custom;

import lk.ijse.tailorshop.bo.SuperBO;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {


    public List<CustomerDTO> getAllCustomers() throws SQLException;


    public  boolean saveCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;


    public  boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public Customer searchByIdCustomers(String customerId) throws SQLException ;


    public  boolean deleteCustomers(String customerId) throws SQLException ;
}
