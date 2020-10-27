package app;

import controller.GameController;
import dao.GameDAO;
import dao.MooDAOJdbcImpl;
import model.GameLogic;
import model.MooLogic;
import view.SimpleWindow;
import view.Ui;

public class MainMoo {

	public static void main(String[] args) {
		Ui ui = new SimpleWindow("Moo Game");
		GameLogic gameLogic = new MooLogic();
		GameDAO gameDAO = new MooDAOJdbcImpl();
		GameController controller = new GameController(ui, gameLogic, gameDAO);
		controller.run();
	}

}
