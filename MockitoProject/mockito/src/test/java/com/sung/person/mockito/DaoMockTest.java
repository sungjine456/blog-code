package com.sung.person.mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DaoMockTest {
	@Mock private SqlSession sqlSession;
    
    @InjectMocks private UserDao userDao;
    
    @Test
    public void daoTest(){
    	when(userDao.findUser()).thenReturn(new User("n", "e"));

    	User user = userDao.findUser();
    	assertThat("n", is(user.getName()));
    	assertThat("e", is(user.getEmail()));
    }
}
