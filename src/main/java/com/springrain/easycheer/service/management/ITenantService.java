package com.springrain.easycheer.service.management;

import com.springrain.easycheer.model.Tenant;

public interface ITenantService {
	
	public Tenant create(Tenant tenant);
	
	public Tenant get(String tenantId);
}
