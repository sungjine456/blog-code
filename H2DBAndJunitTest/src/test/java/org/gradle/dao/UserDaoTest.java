package org.gradle.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.gradle.config.AppConfig;
import org.gradle.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao; 
	
	@Test
	@Transactional
	public void findUserByIdTest(){
		UserVo user = userDao.findUserById(1L);
		assertEquals("testName", user.getName());
		assertEquals("testEmail@test.com", user.getEmail());
		assertEquals("testPassword", user.getPassword());
	}
	
	@Test
	@Transactional
	public void findAllUsersest(){
		List<UserVo> users = userDao.findAllUsers();
		assertEquals(1, users.size());
		UserVo user = users.get(0);
		assertEquals("testName", user.getName());
		assertEquals("testEmail@test.com", user.getEmail());
		assertEquals("testPassword", user.getPassword());
	}
	
	@Test
	@Transactional
	public void insertUserTest(){
		List<UserVo> users = userDao.findAllUsers();
		assertEquals(1, users.size());
		UserVo inser = new UserVo("name", "email", "password");
		userDao.insertUser(inser);
		users = userDao.findAllUsers();
		assertEquals(2, users.size());
		UserVo user = users.get(1);
		assertEquals("name", user.getName());
		assertEquals("email", user.getEmail());
		assertEquals("password", user.getPassword());
	}
	
	@Test
	@Transactional
	public void updateUserTest(){
		UserVo user = userDao.findUserById(1L);
		assertEquals("testName", user.getName());
		assertEquals("testEmail@test.com", user.getEmail());
		assertEquals("testPassword", user.getPassword());
		userDao.updateUser(new UserVo(1L, "name", "email", "password"));
		user = userDao.findUserById(1L);
		assertEquals("name", user.getName());
		assertEquals("email", user.getEmail());
		assertEquals("password", user.getPassword());
	}
}
