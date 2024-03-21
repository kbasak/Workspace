package com.hcm.claim.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.hcm.claim.dao.ClaimDao;
import com.hcm.claim.dao.SearchDao;
import com.hcm.claim.entity.ClaimInfo;
import com.hcm.claim.entity.SearchInfo;
import com.hcm.claim.service.ClaimService;
import com.hcm.claim.service.ClaimServiceImpl;

@SpringBootTest
public class ServiceTest {
	
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private ClaimServiceImpl cs;
	
	@MockBean
	private ClaimDao claimDao;
	
	@MockBean
	private SearchDao searchDao;
	
	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException exception=ExpectedException.none();
	
	@Test
	public void testNotNullObject() {
		assertNotNull(cs);
	}
	
	@Test
	public void testForSubmitClaim() throws EntityNotFoundException, EntityExistsException, IOException{
		ClaimInfo claimInfo=new ClaimInfo(1,1,"Dental",10000.00,new Date(),"claim");
		claimInfo.setClaimid(1);
		claimInfo.setMemberid(1);
		claimInfo.setClaimtype("Dental");
		claimInfo.setClaimamount(20000);
		claimInfo.setClaimdate(new Date());
		claimInfo.setRemarks("Good");		
		
		//For submitClaim(ClaimInfo claimInfo)
		Mockito.when(claimDao.save(claimInfo)).thenReturn(claimInfo);		
		assertThat(claimService.submitClaim(claimInfo)).isEqualTo(claimInfo);
		exception.expect(NotFoundException.class);
		
		//For findByClaimid(long claimid)
		Optional<ClaimInfo> claimInfo1=Optional.of(new ClaimInfo());		
		Mockito.when(claimDao.findById(claimInfo.getClaimid())).thenReturn(claimInfo1);		
		assertThat(claimService.findByClaimid(claimInfo.getClaimid())).isEqualTo(claimInfo1);
				
		//For fetchClaims()
		List<ClaimInfo> claimInfo2=List.of(new ClaimInfo());
		Mockito.when(claimDao.findAll()).thenReturn(claimInfo2);
		assertThat(claimService.fetchClaims()).isEqualTo(claimInfo2);
		
		//For searchClaims()
		List<SearchInfo> searchInfo=List.of(new SearchInfo());
		Mockito.when(searchDao.findAll()).thenReturn(searchInfo);
		assertThat(claimService.searchClaims()).isEqualTo(searchInfo);
		
		
	}
}








