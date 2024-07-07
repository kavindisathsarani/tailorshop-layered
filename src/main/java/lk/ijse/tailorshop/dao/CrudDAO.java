package lk.ijse.tailorshop.dao;

import lk.ijse.tailorshop.entity.MaterialDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public  boolean save(T entity) throws SQLException, ClassNotFoundException ;
    public  boolean update(T entity) throws SQLException, ClassNotFoundException;

    public  T searchById(String id) throws SQLException, ClassNotFoundException;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<String> currentId() throws SQLException, ClassNotFoundException;

    ArrayList<String> getIds() throws SQLException, ClassNotFoundException;

    boolean updateQty(ArrayList<MaterialDetail> mdList) throws SQLException, ClassNotFoundException;

    boolean save(ArrayList<MaterialDetail> mdList) throws SQLException, ClassNotFoundException;

    //ArrayList<String> getIds() throws SQLException, ClassNotFoundException;

    // boolean save(String customerId, String name, String gender, String address, int contactNumber, String email);
}
