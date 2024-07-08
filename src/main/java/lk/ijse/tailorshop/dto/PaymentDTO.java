package lk.ijse.tailorshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentDTO {
    private String paymentId;
    private double TotalCost;
    private double amount;
    private double balance;
    private String status;
    private Date date;
    private String orderId;
}
