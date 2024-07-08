package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.OrderDAO;
import lk.ijse.tailorshop.entity.Order;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?,?,?)", entity.getOrderId(),entity.getStartDate(),
                entity.getDueDate(),entity.getStatus(),entity.getCustomerId());

    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT orderId FROM orders");
        ArrayList<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString("orderId"));
        }
        return idList;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateQty(ArrayList<Order> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(ArrayList<Order> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }
}
