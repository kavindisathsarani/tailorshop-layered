package lk.ijse.tailorshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetailDTO {
   private String orderId;
   private String garmentId;
   private int qty;
}
