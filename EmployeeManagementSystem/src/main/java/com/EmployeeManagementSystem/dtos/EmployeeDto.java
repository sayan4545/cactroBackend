package com.EmployeeManagementSystem.dtos;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long empId;
    private String fullName;
    private LocalDate dob;
    private LocalDate doj;
    private String dept;
    private int yearsOfService;
    private String level;
    private String band;
    private BigDecimal grossCtc;
    private BigDecimal basicPay;
    private BigDecimal variablePay;
}
