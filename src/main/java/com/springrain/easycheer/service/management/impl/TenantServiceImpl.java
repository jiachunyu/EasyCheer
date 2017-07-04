package com.springrain.easycheer.service.management.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrain.easycheer.model.Tenant;
import com.springrain.easycheer.repository.TenantRepository;
import com.springrain.easycheer.service.management.TenantService;

@Service
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantRepository repository;

	public TenantRepository getTenantRepository() {
		return repository;
	}

	public void setTenantRepository(TenantRepository tenantRepository) {
		this.repository = tenantRepository;
	}

	@Override
	public Tenant create(Tenant tenant) {
		return repository.save(tenant);
	}

}
