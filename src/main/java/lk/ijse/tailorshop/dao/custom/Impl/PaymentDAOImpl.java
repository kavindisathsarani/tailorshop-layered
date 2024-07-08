package lk.ijse.tailorshop.dao.custom.Impl;

import lk.ijse.tailorshop.dao.custom.PaymentDAO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.entity.Measurement;
import lk.ijse.tailorshop.entity.Payment;
import lk.ijse.tailorshop.util.SQLUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayment = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");

        while (rst.next()) {
            Payment payment = new Payment(rst.getString("paymentId"), rst.getDouble("TotalCost")
                    ,rst.getDouble("amount"), rst.getDouble("balance"),rst.getString("status"),
                    rst.getDate("date"),rst.getString("orderId"));

            allPayment.add(payment);
        }
        return allPayment;

    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ? ,? , ?,?)", entity.getPaymentId(),
                entity.getTotalCost(),entity.getAmount(), entity.getBalance(),entity.getStatus(),
                entity.getDate(),entity.getOrderId());
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET TotalCost = ?, amount = ?, balance = ?, status = ?, date = ? , orderId = ?  WHERE paymentId = ?",
                entity.getTotalCost(),entity.getAmount(), entity.getBalance(),entity.getStatus(),
                entity.getDate(),entity.getOrderId(), entity.getPaymentId());
    }

    @Override
    public Payment searchById(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment WHERE orderId = ?", orderId + "");
        if (rst.next()) {
            return new Payment(
                    rst.getString("paymentId"),
                    rst.getDouble("TotalCost"),
                    rst.getDouble("amount"),
                    rst.getDouble("balance"),
                    rst.getString("status"),
                    rst.getDate("date"),
                    rst.getString("orderId")

            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM payment WHERE orderId = ?", id);
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
    public boolean updateQty(ArrayList<Payment> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(ArrayList<Payment> mdList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }
}
