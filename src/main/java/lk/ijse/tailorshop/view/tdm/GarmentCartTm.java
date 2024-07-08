package lk.ijse.tailorshop.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class GarmentCartTm {
    private String garmentId;
    private String name;
    private String description;
    private String category;
    private String size;
    private int qty;
    private double materialCost;
    private double towage;
    private double totalPrice;
    private double amount;
    private JFXButton btnRemove;
}
