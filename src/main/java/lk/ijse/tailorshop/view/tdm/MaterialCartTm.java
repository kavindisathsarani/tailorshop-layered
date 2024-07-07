package lk.ijse.tailorshop.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class MaterialCartTm {
    private String materialId;
    private String description;
    private double qty;
    private double unitPrice;
    private String customerId;
    private double total;
    private JFXButton btnRemove;


}
