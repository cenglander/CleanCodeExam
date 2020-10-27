package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.GameLogic;
import model.MooLogic;

class MooLogicTest {
	GameLogic logic;

	@BeforeEach
	private void setUp() throws Exception {
		logic = new MooLogic();
	}

	@Test
	void testCheckBullsCowsAllEqual() {
		assertEquals("BBBB,", logic.checkGuess("1234", "1234"));
	}

	@Test
	void testCheckBullsCowsAllMissing() {
		assertEquals(",", logic.checkGuess("1234", "5678"));
	}

	@Test
	void testCheckBullsCowsAllInWrongPlace() {
		assertEquals(",CCCC", logic.checkGuess("1234", "4321"));
	}

	@Test
	void testCheckUniqueNumbersInGenerateAnswerKey() {
		for (int i = 0; i < 2000; i++) {
			String answerKey = logic.generateAnswerKey();
			boolean uniqueAnswerKeyNumbers = isUniqueCharacters(answerKey);
			assertEquals(true, uniqueAnswerKeyNumbers);
		}
	}

	boolean isUniqueCharacters(String str) {
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j))
					return false;
			}
		}
		return true;
	}
	
}




//	logic=mock(MooLogic.class);
//	when(logic.generateAnswerKey()).thenReturn("1234"); 
//	when(logic.checkBullsCows("1234", "1234")).thenReturn("BBBB");

//	@Test
//	void testCheckBullsCowsRanOneTime() {
//		logic.checkBullsCows("1234", "1234");
//		verify(logic,times(1)).checkBullsCows("1234", "1234");
//	}
