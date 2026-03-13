package com.launchpad.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launchpad.api.domain.Organization;
import com.launchpad.api.dto.CreateOrganizationRequest;
import com.launchpad.api.repository.OrganizationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Transactional
    public Organization createOrganization(CreateOrganizationRequest request) {
        if (organizationRepository.existsBySlug(request.getSlug())) {
            throw new IllegalArgumentException(
                "Slug '" + request.getSlug() + "' is already taken"
            );
        }

        Organization org = Organization.builder()
            .name(request.getName())
            .slug(request.getSlug())
            .build();

        return organizationRepository.save(org);
    }

    @Transactional(readOnly = true)
    public Optional<Organization> findBySlug(String slug) {
        return organizationRepository.findBySlug(slug);
    }
}