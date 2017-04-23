package com.sung.person.mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MockTest {

	@SuppressWarnings("unchecked")
	@Test
	public void verifyTest() {
		List<String> testMock = mock(ArrayList.class);
		testMock.add("1");
		testMock.add("2");
		testMock.add("3");
		
		verify(testMock, atLeastOnce()).add(anyString());
		verify(testMock, atLeast(3)).add(anyString());
		verify(testMock, atMost(3)).add(anyString());
		verify(testMock, times(3)).add(anyString());

		verify(testMock, times(1)).add("1");
		verify(testMock, times(1)).add("2");
		verify(testMock, times(1)).add("3");

		verify(testMock, never()).add("4");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void whenThenTest() {
		Map<String, String> testMock = mock(Map.class);

		when(testMock.get("id")).thenReturn("id");
		when(testMock.get("pw")).thenReturn("pw");

		assertThat("id", is(testMock.get("id")));
		assertThat("pw", is(testMock.get("pw")));
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected=RuntimeException.class)
	public void throwExceptionTest() {
		Map<String, String> testMock = mock(Map.class);

		when(testMock.get("name4")).thenThrow(new RuntimeException());
		
		testMock.get("name4");
	}
	
	/**
	 * UserDao의 메소드가 어떻게 정의되어 있던지 테스트할 때 정하는 내용으로 값이 나온다.
	 * (UserDao는 User의 name을 "name"으로 강제했는데 목 객체를 만들 때 "nm"을 넣으니 nm이 리턴된다)
	 */
	@Test
	public void answerTest(){
		UserDao mockUser = mock(UserDao.class);
		
		when(mockUser.findUser("em")).thenAnswer(new Answer<User>() {
			public User answer(InvocationOnMock invocation) throws Throwable {
				return new User("nm", "email");
			}
		});
		
		User user = mockUser.findUser("em");
		assertThat("nm", is(user.getName()));
		assertThat("email", is(user.getEmail()));
	}
	
	@Test
	public void spyTest(){
		List<Integer> list = new ArrayList<>();
		List<Integer> spy = Mockito.spy(list);

		when(spy.size()).thenReturn(100); // stubbing

		assertThat(spy.size(), is(100));

		spy.add(1);
		spy.add(2);

		verify(spy).add(1);
		verify(spy).add(2);
	}
}
