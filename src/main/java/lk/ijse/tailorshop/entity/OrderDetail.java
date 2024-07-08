package lk.ijse.tailorshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetail {
   private String orderId;
   private String garmentId;
   private int qty;
}
