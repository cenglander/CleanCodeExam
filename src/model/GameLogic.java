package model;

public interface GameLogic {

	String generateAnswerKey();

	String checkGuess(String answerKey, String guess);

}