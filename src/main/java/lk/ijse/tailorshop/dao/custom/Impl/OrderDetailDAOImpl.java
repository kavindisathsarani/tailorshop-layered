package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.OrderDetailDAO;
import lk.ijse.tailorshop.entity.MaterialDetail;
import lk.ijse.tailorshop.entity.OrderDetail;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO{
    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderDetail VALUES(?, ?, ?)", entity.getOrderId(),
                entity.getGarmentId(),entity.getQty());
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> currentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateQty(ArrayList<OrderDetail> mdList) throws SQLException, ClassNotFoundException {
        for (OrderDetail md : mdList) {
            if(!updateQty(md)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(OrderDetail md) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE garment SET qtyOnHand = qtyOnHand - ? WHERE garmentId = ?";
        // Execute update statement with parameters
        // Replace SQLUtil.execute() with your actual method to execute SQL statements
        return SQLUtil.execute(sql, md.getQty(), md.getGarmentId());
    }

    @Override
    public boolean save(ArrayList<OrderDetail> mdList) throws SQLException, ClassNotFoundException {
        for (OrderDetail od : mdList) {
            if(!save(od)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }
}
