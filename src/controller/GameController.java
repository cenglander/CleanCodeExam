package controller;

import java.util.List;

import javax.swing.JOptionPane;

import dao.MooDAO;
import model.MooLogic;
import model.PlayerAverage;
import view.Ui;

public class GameController {

	Ui ui;
	MooLogic mooLogic;
	MooDAO mooDAO;
	

	public GameController(Ui ui, MooLogic mooLogic, MooDAO mooDAO) {
		this.ui = ui;
		this.mooLogic = mooLogic;
		this.mooDAO = mooDAO;
	}

	public void run() {
		int continueGame = JOptionPane.YES_OPTION;
		// login
		ui.addString("Enter your user name:\n");
		String name = ui.getString();
		int userId = mooDAO.getUserByName(name);
		
		while (continueGame == JOptionPane.YES_OPTION) {
			String answerKey = MooLogic.generateAnswerKey();
			ui.clear();
			ui.addString("New game:\n");
			// comment out or remove next line to play real games!
			ui.addString("For practice, number is: " + answerKey + "\n");
			String guess = ui.getString();
			ui.addString(guess + "\n");
			int numOfGuesses = 1;
			String bbcc = MooLogic.checkBC(answerKey, guess);
			ui.addString(bbcc + "\n");
			while (!bbcc.equals("BBBB,")) {
				numOfGuesses++;
				guess = ui.getString();
				ui.addString(guess + "\n");
				bbcc = MooLogic.checkBC(answerKey, guess);
				ui.addString(bbcc + "\n");
			}
			mooDAO.saveResultForUser(userId, numOfGuesses);
			
			showTopTen();
			continueGame = JOptionPane.showConfirmDialog(null, "Correct, it took " + numOfGuesses + " guesses\nContinue?");

		}
		ui.exit();
	}
	
	public void showTopTen() {
		
		List<PlayerAverage> topList = null;
		try {
			topList = mooDAO.getTopTen();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			ui.addString("Unable to get Top Ten List");
		}
		int position = 1;
		ui.addString("Top Ten List\n    Player     Average\n");
		for (PlayerAverage player : topList) {
			ui.addString(String.format("%3d %-10s%5.2f%n", position, player.getName(), player.getAverage()));
		}
	}

}
