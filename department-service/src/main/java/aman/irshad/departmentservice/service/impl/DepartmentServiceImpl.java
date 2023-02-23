package aman.irshad.departmentservice.service.impl;

import aman.irshad.departmentservice.dto.DepartmentDto;
import aman.irshad.departmentservice.entity.Department;
import aman.irshad.departmentservice.repository.DepartmentRepository;
import aman.irshad.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;


    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        //convert departmentDto to department JPA Entity
        Department department = new Department( departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(), departmentDto.getDepartmentCode());

        Department savedDepartment = departmentRepository.save(department);

        //convert department JPA Entity to departmentDto
        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(), savedDepartment.getDepartmentName(), savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );
        return savedDepartmentDto;

    }
}
