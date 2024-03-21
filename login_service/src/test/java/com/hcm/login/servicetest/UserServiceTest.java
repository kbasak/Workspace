package com.hcm.login.servicetest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hcm.login.dao.UserDao;
import com.hcm.login.entity.Users;
import com.hcm.login.service.UserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserDao userDao;

	@Test
	void testforUser() throws EntityNotFoundException, EntityExistsException, IOException {
		Users user = new Users();
		user.setUsername("Kausik");
		user.setPassword("1234");
		user.setUserrole("admin");

		//For addUsers(Users users)
		Mockito.when(userDao.save(user)).thenReturn(user);
		assertThat(userService.addUsers(user)).isEqualTo(user);
		
		//For getUsers()
		List<Users> users=List.of(new Users());
		Mockito.when(userDao.findAll()).thenReturn(users);
		assertThat(userService.getUsers()).isEqualTo(users);
		
		//For existByUserName(Users users)
		Mockito.when(userDao.existsById(user.getUsername())).thenReturn(true);
		assertThat(userService.existByUserName(user.getUsername())).isTrue();
	}
}
