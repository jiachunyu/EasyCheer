package com.springrain.easycheer.rest.management;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springrain.easycheer.model.Tenant;

@RestController
@RequestMapping(value="/management/tenant")
public class TenantManagementController {

	@RequestMapping(method=RequestMethod.POST)
	public Tenant create(@RequestBody Tenant tenant) {
		tenant.setId("1");
		return tenant;
	}
}
