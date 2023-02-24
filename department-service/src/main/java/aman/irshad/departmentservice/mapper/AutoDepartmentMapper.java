package aman.irshad.departmentservice.mapper;

import aman.irshad.departmentservice.dto.DepartmentDto;
import aman.irshad.departmentservice.entity.Department;
import org.mapstruct.factory.Mappers;

public interface AutoDepartmentMapper {

    AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class);
    DepartmentDto mapToDepartmentDto(Department department);
    Department mapToDepartment(DepartmentDto departmentDto);
}
