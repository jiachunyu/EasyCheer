package com.springrain.easycheer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springrain.easycheer.model.Tenant;

public interface TenantRepository extends PagingAndSortingRepository<Tenant, String> {

}
