package aman.irshad.organizationservice.mapper;

import aman.irshad.organizationservice.dto.OrganizationDto;
import aman.irshad.organizationservice.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoOrganizationMapper {

    AutoOrganizationMapper MAPPER = Mappers.getMapper(AutoOrganizationMapper.class);
    OrganizationDto mapToOrganizationDto(Organization organization);
    Organization mapToOrganization(OrganizationDto organizationDto);
}
