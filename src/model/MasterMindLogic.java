package model;

import model.common.RandomGenerator;

public class MasterMindLogic implements GameLogic {

	@Override
	public String generateAnswerKey() {
		String answerKey = "";
		String randomDigit = "";
		int randomNum = 0;

		for (int i = 0; i < 4; i++) {
			randomNum = RandomGenerator.getRandomNumber(1, 6);
			randomDigit = String.valueOf(randomNum);
			answerKey = answerKey + randomDigit;
		}
		return answerKey;
	}

	@Override
	public String checkGuess(String answerKey, String guess) {
		int whites = 0, reds = 0;
		for (int i = 0; i < answerKey.length(); i++) {
			for (int j = 0; j < answerKey.length(); j++) {
				try {
					if (answerKey.charAt(i) == guess.charAt(j)) {
						if (i == j) {
							reds++;
						} else if (!(answerKey.charAt(j) == guess.charAt(j))) {
							whites++;
						}
					}
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException("MasterMindLogic - Error in checkCheckGuess()" + e);
				}
			}
		}
		System.out.println("W " + whites);
		System.out.println("R " + reds);
		String result = "";
		for (int i = 0; i < reds; i++) {
			result = result + "R";
		}
		result = result + ",";
		for (int i = 0; i < whites; i++) {
			result = result + "W";
		}
		return result;
	}

	@Override
	public boolean isIncorrectGuess(String answerKey, String guess) {
		return !(answerKey.equalsIgnoreCase(guess));
	}

}
