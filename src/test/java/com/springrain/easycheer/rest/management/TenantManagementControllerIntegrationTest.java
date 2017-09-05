package com.springrain.easycheer.rest.management;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.springrain.easycheer.model.Tenant;
import com.springrain.easycheer.rest.ErrorResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TenantManagementControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate template;
	
	@Test
	public void testCreateGet() {
		Tenant requestTenant = new Tenant();
		requestTenant.setName("test name");
		requestTenant.setLicenseDate(new Date());
		requestTenant.setLicenseNumber(100);
	
		Tenant responseTenant = template.postForObject("/management/tenant", requestTenant, Tenant.class);
		assertNotNull(responseTenant.getId());
	}
	
	@Test
	public void testGet_InvalidId() {
		ResponseEntity<ErrorResponse> responseEntity = template.getForEntity("/management/tenant/invalid_tenant_id",
				ErrorResponse.class, new HashMap<String, String>());
	}

}
