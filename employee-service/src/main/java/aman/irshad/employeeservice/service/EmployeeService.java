package aman.irshad.employeeservice.service;

import aman.irshad.employeeservice.dto.APIResponseDto;
import aman.irshad.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
