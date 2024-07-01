package lk.ijse.tailorshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Employee {
    private String employeeId;
    private String name;
    private String address;
    private int contactNumber;
    private String position;
}
