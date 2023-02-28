package aman.irshad.employeeservice.service;

import aman.irshad.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Eureka Server comes with out of the box Load Balancer
// We can make call to Instance ID, Internally will use Load Balancer
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    // Build get department REST API
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);

}
