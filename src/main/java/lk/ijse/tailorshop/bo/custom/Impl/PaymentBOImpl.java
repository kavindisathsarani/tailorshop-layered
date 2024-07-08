package lk.ijse.tailorshop.bo.custom.Impl;

import lk.ijse.tailorshop.bo.custom.PaymentBO;
import lk.ijse.tailorshop.dao.DAOFactory;
import lk.ijse.tailorshop.dao.custom.MeasurementDAO;
import lk.ijse.tailorshop.dao.custom.PaymentDAO;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.dto.PaymentDTO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> allPayments= new ArrayList<>();

        ArrayList<Payment> all = paymentDAO.getAll();

        for (Payment c : all) {
            allPayments.add(new PaymentDTO(c.getPaymentId(),c.getTotalCost(),c.getAmount(),c.getBalance(),
                    c.getStatus(), c.getDate(),c.getOrderId()));
        }
        return allPayments;
    }

    @Override
    public boolean savePayments(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getPaymentId(),
                dto.getTotalCost(), dto.getAmount(), dto.getBalance(), dto.getStatus(), dto.getDate(),dto.getOrderId()));

    }

    @Override
    public PaymentDTO searchByIdPayment(String orderId) throws SQLException, ClassNotFoundException {
        Payment c = paymentDAO.searchById(orderId);
        return new PaymentDTO(c.getPaymentId(),c.getTotalCost(),c.getAmount(),c.getBalance(),c.getStatus(),c.getDate(),
                c.getOrderId());
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPaymentId(),
                dto.getTotalCost(), dto.getAmount(), dto.getBalance(), dto.getStatus(), dto.getDate(),dto.getOrderId()));
    }

    @Override
    public boolean deletePayment(String orderId) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(orderId);

    }


}
