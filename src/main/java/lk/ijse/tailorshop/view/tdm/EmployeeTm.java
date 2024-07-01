package lk.ijse.tailorshop.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeTm {
    private String employeeId;
    private String name;
    private String address;
    private int contactNumber;
    private String position;
}
