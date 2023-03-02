package aman.irshad.employeeservice.service.impl;

import aman.irshad.employeeservice.dto.APIResponseDto;
import aman.irshad.employeeservice.dto.DepartmentDto;
import aman.irshad.employeeservice.dto.EmployeeDto;
import aman.irshad.employeeservice.entity.Employee;
import aman.irshad.employeeservice.exception.ResourceNotFoundException;
import aman.irshad.employeeservice.mapper.AutoEmployeeMapper;
import aman.irshad.employeeservice.repository.EmployeeRepository;
import aman.irshad.employeeservice.service.APIClient;
import aman.irshad.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    private WebClient webClient;

    private APIClient apiClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

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
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    //@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",employeeId)
        );

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();
        LOGGER.info("Inside getEmployeeById() Method");
       DepartmentDto departmentDto =  webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail()
//        );

//        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }

    // fallback method implementation
    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception){
        LOGGER.info("Inside getDefaultDepartment() Method");

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",employeeId)
        );

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto =  webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto defaultDepartmentDto = new DepartmentDto();
        defaultDepartmentDto.setDepartmentName("R&D Department");
        defaultDepartmentDto.setDepartmentCode("RD001");
        defaultDepartmentDto.setDepartmentDescription("Research and Development Department");

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail()
//        );

//        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(defaultDepartmentDto);
        return apiResponseDto;

    }


}
