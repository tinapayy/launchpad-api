package com.launchpad.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.launchpad.api.dto.CreateOrganizationRequest;
import com.launchpad.api.dto.OrganizationResponse;
import com.launchpad.api.service.OrganizationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationResponse> createOrganization(
        @Valid @RequestBody CreateOrganizationRequest request
    ) {
        var org = organizationService.createOrganization(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(OrganizationResponse.from(org));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<OrganizationResponse> getOrganization(
        @PathVariable String slug
    ) {
        return organizationService.findBySlug(slug)
            .map(org -> ResponseEntity.ok(OrganizationResponse.from(org)))
            .orElse(ResponseEntity.notFound().build());
    }
}