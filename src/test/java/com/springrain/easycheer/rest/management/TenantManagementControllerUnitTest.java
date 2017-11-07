package com.springrain.easycheer.rest.management;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
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
		ObjectNode jsonRequest = jsonNodeFactory.objectNode();
		jsonRequest.put("name", "test name");
		jsonRequest.put("licenseDate", "2017-01-01");
		jsonRequest.put("licenseNumber", 100);
		
		Tenant serviceReturnedTenant = new Tenant();
		serviceReturnedTenant.setName("test name");
		serviceReturnedTenant.setLicenseDate(Date.valueOf("2017-01-01"));
		serviceReturnedTenant.setLicenseNumber(100);
		serviceReturnedTenant.setId("abc123");
		when(tenantService.create(any())).thenReturn(serviceReturnedTenant);
		
		MvcResult mvcResult = mockMvc
				.perform(post("/management/tenants")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(jsonRequest)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		
		Tenant expectedServiceArgument = new Tenant();
		expectedServiceArgument.setName("test name");
		expectedServiceArgument.setLicenseDate(Date.valueOf("2017-01-01"));
		expectedServiceArgument.setLicenseNumber(100);
		verify(tenantService).create(argThat(actualServiceArgument -> new EqualsBuilder()
				.append(expectedServiceArgument, actualServiceArgument).build()));

		JsonNode jsonResponse = objectMapper.readTree(mvcResult.getResponse().getContentAsByteArray());
		assertEquals("name is wrong", "test name", jsonResponse.get("name").asText());
		assertEquals("licenseDate is wrong", "2017-01-01", jsonResponse.get("licenseDate").asText());
		assertEquals("licenseNumber is wrong", 100, jsonResponse.get("licenseNumber").asInt());
		assertEquals("id is wrong", "abc123", jsonResponse.get("id").asText());
	}
}