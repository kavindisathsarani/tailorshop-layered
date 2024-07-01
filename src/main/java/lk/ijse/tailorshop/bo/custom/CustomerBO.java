package lk.ijse.tailorshop.bo.custom;

import lk.ijse.tailorshop.bo.SuperBO;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {


    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;


    public  boolean saveCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;


    public  boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public CustomerDTO searchByIdCustomers(String customerId) throws SQLException, ClassNotFoundException;


    public  boolean deleteCustomers(String customerId) throws SQLException, ClassNotFoundException;
}
