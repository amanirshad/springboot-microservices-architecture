package aman.irshad.employeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

//    public Employee(String firstName, String lastName, String email, String departmentCode){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.departmentCode = departmentCode;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    private String departmentCode;
    private String organizationCode;
}
