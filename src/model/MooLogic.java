package model;

import model.common.RandomGenerator;

public class MooLogic implements GameLogic {

	@Override
	public String generateAnswerKey() {
		String answerKey = "";
		String randomDigit = "";
		int randomNum = 0;

		for (int i = 0; i < 4; i++) {
			do {
				randomNum = RandomGenerator.getRandomNumber(0, 9);
				randomDigit = String.valueOf(randomNum);
			} while (answerKey.contains(randomDigit));
			answerKey = answerKey + randomDigit;
		}
		return answerKey;
	}

	@Override
	public String checkGuess(String answerKey, String guess) {
		int cows = 0, bulls = 0;
		for (int i = 0; i < answerKey.length(); i++) {
			for (int j = 0; j < answerKey.length(); j++) {
				try {
					if (answerKey.charAt(i) == guess.charAt(j)) {
						if (i == j) {
							bulls++;
						} else {
							cows++;
						}
					}
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException("MooLogic - Error in checkBullsCows()" + e);
				}
			}
		}
		String result = "";
		for (int i = 0; i < bulls; i++) {
			result = result + "B";
		}
		result = result + ",";
		for (int i = 0; i < cows; i++) {
			result = result + "C";
		}
		return result;
	}

	@Override
	public boolean isIncorrectGuess(String answerKey, String guess) {
		return !(answerKey.equalsIgnoreCase(guess));
	}

}
