package lk.ijse.tailorshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOrder {
    private Order order;
    private ArrayList<OrderDetail> odList;
}
