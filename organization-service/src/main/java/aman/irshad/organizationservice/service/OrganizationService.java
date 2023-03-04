package aman.irshad.organizationservice.service;

import aman.irshad.organizationservice.dto.OrganizationDto;
import aman.irshad.organizationservice.entity.Organization;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode(String organizationCode);
}
