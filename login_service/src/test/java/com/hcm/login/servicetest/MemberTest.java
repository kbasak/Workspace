
package com.hcm.login.servicetest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hcm.login.dao.MemberDao;
import com.hcm.login.entity.Member;
import com.hcm.login.service.MemberService;

@SpringBootTest
class MemberTest {

	@Autowired
	private MemberService memberService;

	@MockBean
	private MemberDao memberDao;

	@Test
	void testMember() throws EntityNotFoundException, EntityExistsException, IOException {
		Member member = new Member("Kausik", "Kausik", "Basak", DateUtil.parse("1998-10-08"),  "WB",
				"Kolkata", "abc@outlook.com", 1);
		List<Member> member1 = List.of(new Member());

		// For getMember()
		Mockito.when(memberDao.findAll()).thenReturn(member1);
		assertThat(memberService.getMember()).isEqualTo(member1);

		// For addMember(Member member)
		Mockito.when(memberDao.save(member)).thenReturn(member);
		assertThat(memberService.addMember(member)).isEqualTo(member);

		// For countByUserName
		Mockito.when(memberDao.existsByUsername("Kausik")).thenReturn(true);
		Mockito.when(memberDao.existsByUsername("abcdef")).thenReturn(false);
		assertThat(memberService.countByUserName("Kausik")).isTrue();
		assertThat(memberService.countByUserName("abcdef")).isFalse();
	}
}
