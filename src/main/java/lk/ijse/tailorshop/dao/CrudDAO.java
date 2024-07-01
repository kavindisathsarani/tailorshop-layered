package lk.ijse.tailorshop.dao;

import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public List<T> getAll() throws SQLException ;

    public  boolean save(Customer entity) throws SQLException, ClassNotFoundException ;
    public  boolean update(T entity) throws SQLException, ClassNotFoundException;

    public  T searchById(String customerId) throws SQLException;
    public  boolean delete(String customerId) throws SQLException;

   // boolean save(String customerId, String name, String gender, String address, int contactNumber, String email);
}
