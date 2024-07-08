package lk.ijse.tailorshop.bo.custom;

import lk.ijse.tailorshop.bo.SuperBO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.entity.Garment;
import lk.ijse.tailorshop.entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {
    ArrayList<String> currentOrderId() throws SQLException, ClassNotFoundException;

    ArrayList<String> getGarmentIDs() throws SQLException, ClassNotFoundException;

    Garment searchByCodeGarments(String garmentId) throws SQLException, ClassNotFoundException;

    public  boolean placeOrder(PlaceOrder po) throws SQLException ;
}
