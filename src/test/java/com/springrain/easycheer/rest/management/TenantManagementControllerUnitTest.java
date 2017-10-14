package com.springrain.easycheer.rest.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeClass
	public static void setup() {
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	@Test
	public void create() throws Exception {
		Tenant requestTenant = new Tenant();
		requestTenant.setName("test name");
		requestTenant.setLicenseDate(new Date());
		requestTenant.setLicenseNumber(100);
		JsonNode jsonNode = objectMapper.valueToTree(requestTenant);
		((ObjectNode) jsonNode).remove("id");

		/*
		given(tenantService.create(requestTenant)).willReturn(requestTenant);
		MvcResult mvcResult = mockMvc
				.perform(post("/management/tenants").contentType(MEDIA_TYPE_JSON).content(objectMapper.writeValueAsBytes(jsonNode)))
				.andDo(print())
				.andExpect(status().isOk())
			//	.andExpect(content().contentType(MEDIA_TYPE_JSON))
				.andReturn();
		Tenant responseTenant = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), Tenant.class);

		assertTrue(new EqualsBuilder().setTestRecursive(true).setExcludeFields("id")
				.append(requestTenant, responseTenant).build());
		assertEquals("1", responseTenant.getId());
		*/
	}
}