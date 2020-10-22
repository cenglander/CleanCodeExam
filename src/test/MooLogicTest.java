package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import model.MooLogic;

class MooLogicTest {
MooLogic logic;
	@BeforeEach
	private void setUp() throws Exception {
		logic=mock(MooLogic.class);
		   when(logic.generateAnswerKey()).thenReturn("1234"); 
		   
		   when(logic.checkBullsCows("1234", "1234")).thenReturn("BBBB");
		   when(logic.checkBullsCows("1234", "5678")).thenReturn(",");
		   when(logic.checkBullsCows("1234", "4321")).thenReturn("CCCC");
	        }
	
	@Test
	void testEqualAnswerKey() {
		assertEquals("1234",logic.generateAnswerKey());
	}
	@Test
	void testNotEqualAnswerKey() {
		assertNotEquals("1875",logic.generateAnswerKey());
	}
	@Test
	void testCheckBullsCowsAllEqual() {
		assertEquals("BBBB",logic.checkBullsCows("1234", "1234"));
	}
	@Test
	void testCheckBullsCowsAllMissing() {
		assertEquals(",",logic.checkBullsCows("1234", "5678"));
	}
	@Test
	void testCheckBullsCowsAllInWrongPlace() {
		assertEquals("CCCC",logic.checkBullsCows("1234", "4321"));
	}
	
	@Test
	void testGenerateAnswerKeyRanOneTime() {
		logic.generateAnswerKey();
		verify(logic,times(1)).generateAnswerKey();
	}
	@Test
	void testCheckBullsCowsRanOneTime() {
		logic.checkBullsCows("1234", "1234");
		verify(logic,times(1)).checkBullsCows("1234", "1234");
	}
	
}
