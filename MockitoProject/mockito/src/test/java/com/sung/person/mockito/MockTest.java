package com.sung.person.mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MockTest {

	/**
	 * verify 메소드를 목 객체만 넣어서 호출한다면 verify(mockObject, times(1)) 과 같다.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void verifyTest() {
		List<String> testMockList = mock(ArrayList.class);
		testMockList.add("1");
		testMockList.add("2");
		testMockList.add("3");
		testMockList.add("3");
		
		verify(testMockList, atLeastOnce()).add("1");      // 적어도 한번 호출되었는지
		verify(testMockList, atLeast(2)).add("3");         // n번 째 호출이 있었는지
		verify(testMockList, atMost(4)).add(anyString());  // 최대 호출 
		verify(testMockList, atMost(4)).add("123");        // add의 파라미터는 무엇이 되도 상관없다
		
		verify(testMockList, times(1)).add("1");
		verify(testMockList, times(2)).add("3");
		verify(testMockList, times(4)).add(anyString());      // 동일한 값이 몇번 호출 됐는지

		verify(testMockList, never()).add("4");  // times(0)과 같다
		verify(testMockList, times(0)).add("4");
		
		assertNull("1", testMockList.get(0)); // get 메소드는 정의하지않아서 Null이다.
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void whenThenTest() {
		Map<String, String> testMockMap = mock(Map.class);
		
		when(testMockMap.get("id")).thenReturn("id");
		when(testMockMap.get("pw")).thenReturn("pw");
		
		verify(testMockMap, atMost(2)).get(anyString());
		
		assertThat("id", is(testMockMap.get("id")));
		assertThat("pw", is(testMockMap.get("pw")));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void whenThenVerifyException(){
		List<Integer> testMockList = mock(ArrayList.class);
		testMockList.add(1);
		when(testMockList.size()).thenReturn(100);
		
		verify(testMockList).add(1);
//		verify(testMockList).size();  testMockList로 size메소드를 호출한 적이 없기 때문에 에러가 난다.
		assertEquals(100, testMockList.size());
		verify(testMockList).size();  // size메소드를 호출한 적이 있기 때문에 에러가 나지 않는다.
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected=RuntimeException.class)
	public void throwExceptionTest() {
		Map<String, String> testMockMap = mock(Map.class);

		when(testMockMap.get("name4")).thenThrow(new RuntimeException());
		
		testMockMap.get("name4");
	}
	
	/**
	 * UserDao의 메소드가 어떻게 정의되어 있던지 테스트할 때 정하는 내용으로 값이 나온다.
	 * (UserDao는 User의 name을 "name"으로 강제했는데 목 객체를 만들 때 "nm"을 넣으니 nm이 리턴된다)
	 */
	@Test
	public void answerTest(){
		UserDao testMockUser = mock(UserDao.class);
		
		when(testMockUser.findUser("em")).thenAnswer(new Answer<User>() {
			public User answer(InvocationOnMock invocation) throws Throwable {
				return new User("nm", "email");
			}
		});
	    
		User user = testMockUser.findUser("em");
		assertThat("nm", is(user.getName()));
		assertThat("email", is(user.getEmail()));
	}
}
