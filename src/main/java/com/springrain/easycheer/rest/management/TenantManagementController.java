package com.springrain.easycheer.rest.management;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springrain.easycheer.exception.ErrorCode;
import com.springrain.easycheer.exception.ObjectNotFoundException;
import com.springrain.easycheer.model.Tenant;
import com.springrain.easycheer.service.management.ITenantService;

@RestController
@RequestMapping(value="/management/tenants")
public class TenantManagementController {

	@Autowired
	private ITenantService tenantService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Tenant> create(@RequestBody Tenant tenant) {
		return new ResponseEntity<Tenant>(tenantService.create(tenant), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{tenantId}", method=RequestMethod.GET)
	public Tenant get(@PathVariable String tenantId) throws ObjectNotFoundException {
		return Optional.ofNullable(tenantService.get(tenantId))
				.orElseThrow(()->new ObjectNotFoundException(ErrorCode.TENANT_NOT_FOUND, "Can not find tenant with id " + tenantId));
	}
}
