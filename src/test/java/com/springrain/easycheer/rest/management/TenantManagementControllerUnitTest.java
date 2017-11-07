package com.springrain.easycheer.rest.management;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springrain.easycheer.model.Tenant;
import com.springrain.easycheer.service.management.ITenantService;

@RunWith(SpringRunner.class)
@WebMvcTest(TenantManagementController.class)
public class TenantManagementControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ITenantService tenantService;

	private static final JsonNodeFactory jsonNodeFactory = new JsonNodeFactory(false);
	private static final ObjectMapper objectMapper = new ObjectMapper();


	@Test
	public void create() throws Exception {
		ObjectNode requestTenant = jsonNodeFactory.objectNode();
		requestTenant.put("name", "test name");
		requestTenant.put("LicenseDate", "2017-01-01");
		requestTenant.put("licenseNumber", 100);
		
		Tenant returnedTenant = new Tenant();
		returnedTenant.setName("test name");
		returnedTenant.setLicenseDate(LocalDate.of(2017, 1, 1));
		returnedTenant.setLicenseNumber(100);
		returnedTenant.setId("abc123");
		when(tenantService.create(any())).thenReturn(returnedTenant);
		
		MvcResult mvcResult = mockMvc
				.perform(post("/management/tenants")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(requestTenant)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		
		Tenant givenTenant = new Tenant();
		givenTenant.setName("test name");
		givenTenant.setLicenseDate(LocalDate.of(2017, 1, 1));
		givenTenant.setLicenseNumber(100);
		//verify(tenantService).create(givenTenant);

		JsonNode responseTenant = objectMapper.readTree(mvcResult.getResponse().getContentAsByteArray());
		assertEquals("name is wrong", "test name", responseTenant.get("name").asText());
		//assertEquals("licenseDate is wrong", "2017-01-01", responseTenant.get("licenseDate").asText());
		assertEquals("licenseNumber is wrong", 100, responseTenant.get("licenseNumber").asInt());
		assertEquals("id is wrong", "abc123", responseTenant.get("id").asText());
	}
}