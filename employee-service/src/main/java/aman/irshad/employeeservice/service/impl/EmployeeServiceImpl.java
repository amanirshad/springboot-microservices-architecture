package aman.irshad.employeeservice.service.impl;

import aman.irshad.employeeservice.dto.EmployeeDto;
import aman.irshad.employeeservice.entity.Employee;
import aman.irshad.employeeservice.exception.ResourceNotFoundException;
import aman.irshad.employeeservice.mapper.AutoEmployeeMapper;
import aman.irshad.employeeservice.repository.EmployeeRepository;
import aman.irshad.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;


    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

//        Employee employee = new Employee(employeeDto.getFirstName(),
//                employeeDto.getLastName(), employeeDto.getEmail());

//        Employee employee = modelMapper.map(employeeDto,Employee.class);
        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);


        EmployeeDto savedEmployeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
//        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee,EmployeeDto.class);
//        EmployeeDto savedEmployeeDto = new EmployeeDto(savedEmployee.getId(),
//                savedEmployee.getFirstName(),
//                savedEmployee.getLastName(),
//                savedEmployee.getEmail());

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",employeeId)
        );

//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail()
//        );

//        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        return employeeDto;
    }
}
