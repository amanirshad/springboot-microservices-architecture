package aman.irshad.employeeservice.mapper;

import aman.irshad.employeeservice.dto.EmployeeDto;
import aman.irshad.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoEmployeeMapper {

    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);

    EmployeeDto mapToEmployeeDto(Employee employee);
    Employee mapToEmployee(EmployeeDto employeeDto);
}
