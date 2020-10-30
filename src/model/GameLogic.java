package model;

public interface GameLogic {

	String generateAnswerKey();

	String checkGuess(String answerKey, String guess);

	boolean isIncorrectGuess(String answerKey, String guess);

}