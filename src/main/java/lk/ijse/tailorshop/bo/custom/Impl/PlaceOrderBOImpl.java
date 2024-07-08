package lk.ijse.tailorshop.bo.custom.Impl;

import lk.ijse.tailorshop.bo.custom.PlaceOrderBO;
import lk.ijse.tailorshop.dao.DAOFactory;
import lk.ijse.tailorshop.dao.custom.GarmentDAO;
import lk.ijse.tailorshop.dao.custom.OrderDAO;
import lk.ijse.tailorshop.dao.custom.OrderDetailDAO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.dto.PlaceOrderDTO;
import lk.ijse.tailorshop.entity.Garment;
import lk.ijse.tailorshop.entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    GarmentDAO garmentDAO= (GarmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GARMENT);
    OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.OREDER);



    @Override
    public ArrayList<String> currentOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.currentId();
    }

    @Override
    public ArrayList<String> getGarmentIDs() throws SQLException, ClassNotFoundException {
        return garmentDAO.getIds();

    }

    @Override
    public Garment searchByCodeGarments(String garmentId) throws SQLException, ClassNotFoundException {
        return garmentDAO.searchById(garmentId);

    }

    public boolean placeOrder(PlaceOrderDTO po) throws SQLException {
        Connection connection = DbConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDAO.save(po.getOrder());
            if (isOrderSaved) {
                boolean isOrderDetailSaved = orderDetailDAO.save(po.getOdList());
                if (isOrderDetailSaved) {
                    boolean isItemQtyUpdate = orderDetailDAO.updateQty(po.getOdList());
                    if (isItemQtyUpdate) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

}

