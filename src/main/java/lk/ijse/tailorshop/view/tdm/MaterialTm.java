package lk.ijse.tailorshop.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MaterialTm {
    private String materialId;
    private String description;
    private double qty;
    private double unitPrice;
    private String customerId;
}
