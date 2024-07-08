package lk.ijse.tailorshop.dto;

import lk.ijse.tailorshop.entity.Order;
import lk.ijse.tailorshop.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOrderDTO {
    private Order order;
    private List<OrderDetail> odList;
}
