package lk.ijse.tailorshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Garment {
    private String garmentId;
    private String name;
    private String description;
    private String category;
    private String size;
    private double qtyOnHand;
    private double materialCost;
    private double towage;
    private double totalPrice;

}
