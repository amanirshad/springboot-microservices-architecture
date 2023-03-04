package aman.irshad.organizationservice.controller;

import aman.irshad.organizationservice.dto.OrganizationDto;
import aman.irshad.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;

    // Build Save Organization REST API
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

    // Build Get Organization By Code REST API
    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String organizationCode){
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(organizationDto,HttpStatus.OK);
    }
}
