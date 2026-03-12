package com.launchpad.api.dto;

import java.time.Instant;
import java.util.UUID;

import com.launchpad.api.domain.Organization;

import lombok.Data;

@Data
public class OrganizationResponse {

    private UUID id;
    private String name;
    private String slug;
    private String plan;
    private Instant createdAt;
    private Boolean isActive;

    public static OrganizationResponse from(Organization org) {
        OrganizationResponse response = new OrganizationResponse();
        response.setId(org.getId());
        response.setName(org.getName());
        response.setSlug(org.getSlug());
        response.setPlan(org.getPlan().name());
        response.setCreatedAt(org.getCreatedAt());
        response.setIsActive(org.getIsActive());
        return response;
    }
}