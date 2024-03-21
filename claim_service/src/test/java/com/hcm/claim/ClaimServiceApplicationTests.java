package com.hcm.claim;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;


@SpringBootTest
class ClaimServiceApplicationTests {
	
	@Test
	void testMainMethod() throws NumberFormatException, IOException, NotFoundException, ParseException{
		ClaimServiceApplication.main(new String[] {});
	}

}
