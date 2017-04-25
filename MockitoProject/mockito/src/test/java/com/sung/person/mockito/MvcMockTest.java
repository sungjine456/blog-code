package com.sung.person.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MvcMockTest {
	@Mock private UserDao userDao;
	@InjectMocks private UserService userService;
	
	@Test
	public void findUserTest(){
		when(userDao.findUser()).thenReturn(new User("name", "email"));
		
		User user = userService.findUser();
		assertEquals(user.getEmail(), "email");
		assertEquals(user.getName(), "name");
	}
	
	@Test
	public void findUserByEmailTest(){
		when(userDao.findUser("email")).thenReturn(new User("name", "email"));
		
		User user = userService.findUser("email");
		assertEquals(user.getEmail(), "email");
		assertEquals(user.getName(), "name");
	}
}
