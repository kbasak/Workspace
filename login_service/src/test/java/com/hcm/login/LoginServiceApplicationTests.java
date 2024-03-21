package com.hcm.login;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@SpringBootTest
class LoginServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void testMainMethod()
			throws NumberFormatException, IOException, NotFoundException, ParseException {
		LoginServiceApplication.main(new String[] {});
		
	}
}
