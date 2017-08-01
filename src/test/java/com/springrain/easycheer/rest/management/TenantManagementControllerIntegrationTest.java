package com.springrain.easycheer.rest.management;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TenantManagementControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate template;
	
	@Test
	public void testCreateGet() {
		
	}

}
