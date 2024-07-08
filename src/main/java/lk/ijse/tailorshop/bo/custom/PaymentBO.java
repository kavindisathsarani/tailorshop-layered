package lk.ijse.tailorshop.bo.custom;

import lk.ijse.tailorshop.bo.SuperBO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.dto.PaymentDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {

    ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;

    public boolean savePayments(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    PaymentDTO searchByIdPayment(String orderId) throws SQLException, ClassNotFoundException;

    boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    boolean deletePayment(String orderId) throws SQLException, ClassNotFoundException;
}
