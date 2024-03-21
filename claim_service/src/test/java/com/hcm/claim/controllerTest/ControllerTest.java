package com.hcm.claim.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcm.claim.dao.MemberDao;
import com.hcm.claim.entity.ClaimInfo;
import com.hcm.claim.service.ClaimService;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

	@MockBean
	private ClaimService claimService;

	@MockBean
	private MemberDao MemberDao;

	@SuppressWarnings("unused")
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before(value = "ControllerTest")
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testControllers() throws Exception {

		ClaimInfo claimInfo = new ClaimInfo(1, 1, "Dental", 10000.00, new Date(), "claim");
		Mockito.when(claimService.submitClaim(claimInfo)).thenReturn(claimInfo);

		assertThat(claimService.submitClaim(claimInfo)).isEqualTo(claimInfo);

	}

	public static String asJsonString(ClaimInfo claimInfo) {
		try {
			return new ObjectMapper().writeValueAsString(claimInfo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
