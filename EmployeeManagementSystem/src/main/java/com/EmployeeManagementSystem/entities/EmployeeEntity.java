package com.EmployeeManagementSystem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.AccessType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "employee"
)
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
