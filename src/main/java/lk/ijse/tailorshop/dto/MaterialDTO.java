package lk.ijse.tailorshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MaterialDTO {
    private String materialId;
    private String description;
    private double qty;
    private double unitPrice;
    private String customerId;
}
