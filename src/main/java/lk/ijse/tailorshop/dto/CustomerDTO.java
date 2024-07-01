package lk.ijse.tailorshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerDTO {
    private String customerId;
    private String name;
    private String gender;
    private String address;
    private int contactNumber;
    private String email;
}
