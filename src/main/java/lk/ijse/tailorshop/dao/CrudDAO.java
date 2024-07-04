package lk.ijse.tailorshop.dao;

import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public  boolean save(T entity) throws SQLException, ClassNotFoundException ;
    public  boolean update(T entity) throws SQLException, ClassNotFoundException;

    public  T searchById(String id) throws SQLException, ClassNotFoundException;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

   // boolean save(String customerId, String name, String gender, String address, int contactNumber, String email);
}
