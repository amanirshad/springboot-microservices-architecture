package aman.irshad.organizationservice.service.impl;

import aman.irshad.organizationservice.dto.OrganizationDto;
import aman.irshad.organizationservice.entity.Organization;
import aman.irshad.organizationservice.mapper.AutoOrganizationMapper;
import aman.irshad.organizationservice.repository.OrganizationRepository;
import aman.irshad.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = AutoOrganizationMapper.MAPPER.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        return AutoOrganizationMapper.MAPPER.mapToOrganizationDto(savedOrganization);

    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return AutoOrganizationMapper.MAPPER.mapToOrganizationDto(organization);
    }
}
