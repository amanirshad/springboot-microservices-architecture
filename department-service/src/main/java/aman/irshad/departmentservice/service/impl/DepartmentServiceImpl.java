package aman.irshad.departmentservice.service.impl;

import aman.irshad.departmentservice.dto.DepartmentDto;
import aman.irshad.departmentservice.entity.Department;
import aman.irshad.departmentservice.repository.DepartmentRepository;
import aman.irshad.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        //convert departmentDto to department JPA Entity
//        Department department = new Department( departmentDto.getDepartmentName(),
//                departmentDto.getDepartmentDescription(), departmentDto.getDepartmentCode());

        Department department = modelMapper.map(departmentDto,Department.class);

        Department savedDepartment = departmentRepository.save(department);

        //convert department JPA Entity to departmentDto
//        DepartmentDto savedDepartmentDto = new DepartmentDto(
//                savedDepartment.getId(), savedDepartment.getDepartmentName(), savedDepartment.getDepartmentDescription(),
//                savedDepartment.getDepartmentCode()
//        );

        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment,DepartmentDto.class);
        return savedDepartmentDto;

    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

//        DepartmentDto departmentDto = new DepartmentDto(
//                department.getId(), department.getDepartmentName(),
//                department.getDepartmentDescription(),
//                department.getDepartmentCode()
//        );
        DepartmentDto departmentDto = modelMapper.map(department,DepartmentDto.class);
        return departmentDto;

    }
}
