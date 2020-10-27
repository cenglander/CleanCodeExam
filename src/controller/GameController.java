package controller;

import java.util.List;

import javax.swing.JOptionPane;

import dao.GameDAO;
import model.GameLogic;
import model.PlayerAverage;
import view.Ui;

public class GameController {

	Ui ui;
	GameLogic gameLogic;
	GameDAO gameDAO;
	
	public GameController(Ui ui, GameLogic gameLogic, GameDAO gameDAO) {
		this.ui = ui;
		this.gameLogic = gameLogic;
		this.gameDAO = gameDAO;
	}

	public void run() {
		int continueGame = JOptionPane.YES_OPTION;
		// login
		ui.addString("Enter your user name:\n");
		String name = ui.getString();
		int userId = gameDAO.getUserByName(name);
		if (userId==0) {
			ui.addString("User not in database, please register with admin");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ui.exit();
		}
		while (continueGame == JOptionPane.YES_OPTION) {
			String answerKey = gameLogic.generateAnswerKey();
			ui.clear();
			ui.addString("New game:\n");
			// comment out or remove next line to play real games!
			ui.addString("For practice, number is: " + answerKey + "\n");
			String guess = ui.getString();
			ui.addString(guess + "\n");
			int numOfGuesses = 1;
			String answerFeedback = getAnswerKey(answerKey, guess);
			numOfGuesses = showAnswerFeedback(answerKey, numOfGuesses, answerFeedback);
			gameDAO.saveResultForUser(userId, numOfGuesses);
			
			showTopTen();
			continueGame = JOptionPane.showConfirmDialog(null, "Correct, it took " + numOfGuesses + " guesses\nContinue?");

		}
		ui.exit();
	}

	private String getAnswerKey(String answerKey, String guess) {
		String answerFeedback = "";
		try {
			answerFeedback = gameLogic.checkGuess(answerKey, guess);
			ui.addString(answerFeedback + "\n");
			
		} catch (RuntimeException e) {
			ui.addString("Your guess is too short. Please try again.\n");
		}
		return answerFeedback;
	}

	private int showAnswerFeedback(String answerKey, int numOfGuesses, String answerFeedback) {
		String answer;
		while (!answerFeedback.equals("BBBB,")) {
			numOfGuesses++;
			answer = ui.getString();
			ui.addString(answer + "\n");
			answerFeedback = gameLogic.checkGuess(answerKey, answer);
			ui.addString(answerFeedback + "\n");
		}
		return numOfGuesses;
	}
	
	public void showTopTen() {	
		List<PlayerAverage> topList = null;
		try {
			topList = gameDAO.getTopTen();
		} catch (RuntimeException e) {
			ui.addString("Unable to get Top Ten List");
		}
		int position = 1;
		ui.addString("Top Ten List\n    Player     Average\n");
		for (PlayerAverage player : topList) {
			ui.addString(String.format("%3d %-10s%5.2f%n", position, player.getName(), player.getAverage()));
		}
	}

}
