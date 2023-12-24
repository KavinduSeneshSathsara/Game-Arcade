package lk.ijse.GameCafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeDto {

    private String empId;
    private String empName;
    private String empContactNum;
    private String empSalary;
    private String empAddress;

}
