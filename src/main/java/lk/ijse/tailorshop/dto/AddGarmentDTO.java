package lk.ijse.tailorshop.dto;

import lk.ijse.tailorshop.entity.Garment;
import lk.ijse.tailorshop.entity.MaterialDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class AddGarmentDTO {
 private Garment garment;
    private ArrayList<MaterialDetail> mdList;

}
