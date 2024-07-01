package lk.ijse.tailorshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDTO {
    private String employeeId;
    private String name;
    private String address;
    private int contactNumber;
    private String position;
}
