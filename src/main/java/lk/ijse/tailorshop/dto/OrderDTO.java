package lk.ijse.tailorshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDTO {
    private String orderId;
    private Date startDate;
    private Date dueDate;
    private String status;
    private String customerId;

}
