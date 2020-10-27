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
		if (userId == 0) {
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
			ui.addString("For practice, number is: " + answerKey + "\n");
			String guess = "";
			int numOfGuesses = 0;
			String answerFeedback = "";
			do {
				guess = ui.getString();
				ui.addString(guess + "\n");
				numOfGuesses++;
				try {
					answerFeedback = gameLogic.checkGuess(answerKey, guess);
				} catch (RuntimeException e) {
					ui.addString("Your guess is not valid. Please try again.\n");
				}
				ui.addString(answerFeedback + "\n");
			} while (gameLogic.isIncorrectGuess(answerKey, guess));
			gameDAO.saveResultForUser(userId, numOfGuesses);
			showTopTen();
			continueGame = JOptionPane.showConfirmDialog(null,
					"Correct, it took " + numOfGuesses + " guesses\nContinue?");
		}
		ui.exit();
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
