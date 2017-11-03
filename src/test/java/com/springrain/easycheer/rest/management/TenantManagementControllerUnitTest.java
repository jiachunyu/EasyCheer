package com.springrain.easycheer.rest.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springrain.easycheer.model.Tenant;
import com.springrain.easycheer.rest.RestUtil;
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
		ObjectNode jsonTenant = jsonNodeFactory.objectNode();
		jsonTenant.put("name", "test name");
		jsonTenant.put("licenseDate", "");
		jsonTenant.put("licenseNumber", 100);
		String jsonString = objectMapper.writeValueAsString(jsonTenant);

		when(tenantService.create(isNotNull())).thenReturn(any());
		
		MvcResult mvcResult = mockMvc
				.perform(post("/management/tenants")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonString))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		Tenant responseTenant = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), Tenant.class);

		/*
		assertTrue(new EqualsBuilder().setTestRecursive(true).setExcludeFields("id")
				.append(requestTenant, responseTenant).build());
		assertEquals("1", responseTenant.getId());
		*/
	}
}