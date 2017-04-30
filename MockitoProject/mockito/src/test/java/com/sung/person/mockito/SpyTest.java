package com.sung.person.mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SpyTest {
	
	private List<Integer> list;
	private List<Integer> spy;
	
	@Before
	public void setup(){
		list = new ArrayList<>();
		spy = Mockito.spy(list);
	}

	// spy는 실제 객체처럼 동작한다.
	@Test
	public void spyTest(){
		when(spy.size()).thenReturn(100);

		spy.add(1);
		spy.add(2);

		assertEquals(spy.size(), 100);
		assertThat(spy.get(0), is(1));

		verify(spy).add(1);
		verify(spy).add(2);
	}
	
	// 실제로 들어 있는 값이 없기 때문에 에러가 발생할 것이다.
	@Test(expected=IndexOutOfBoundsException.class)
	public void spyThenReturnTest(){
		when(spy.get(0)).thenReturn(1);

	    assertThat(1, is(spy.get(0)));
	}
	
	@Test
	public void spyDoReturnTest(){
	    doReturn(1).when(spy).get(0);

	    assertThat(1, is(spy.get(0)));
	}
}
