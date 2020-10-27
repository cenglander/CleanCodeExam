package app;

import controller.GameController;
import dao.GameDAO;
import dao.MasterMindDAOJdbcImpl;
import dao.MooDAOJdbcImpl;
import model.GameLogic;
import model.MasterMindLogic;
import model.MooLogic;
import view.SimpleWindow;
import view.Ui;

public class MainMasterMind {

	public static void main(String[] args) {
		Ui ui = new SimpleWindow("Master Mind Game");
		GameLogic gameLogic = new MasterMindLogic();
		GameDAO gameDAO = new MasterMindDAOJdbcImpl();
		GameController controller = new GameController(ui, gameLogic, gameDAO);
		controller.run();
	}

}
