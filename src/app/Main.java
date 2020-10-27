package app;

import controller.GameController;
import dao.GameDAO;
import dao.MooDAOJdbcImpl;
import model.GameLogic;
import model.MooLogic;
import view.SimpleWindow;
import view.Ui;

public class Main {

	public static void main(String[] args) {
		Ui ui = new SimpleWindow("Moo Game");
		GameLogic mooLogic = new MooLogic();
		GameDAO mooDAO = new MooDAOJdbcImpl();
		GameController controller = new GameController(ui, mooLogic, mooDAO);
		controller.run();
	}

}
