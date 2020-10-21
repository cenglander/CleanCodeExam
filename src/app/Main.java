package app;

import controller.GameController;
import dao.MooDAO;
import dao.MooDAOJdbcImpl;
import model.MooLogic;
import view.SimpleWindow;
import view.Ui;

public class Main {

	public static void main(String[] args) {
		Ui ui = new SimpleWindow("Moo Game");
		MooLogic mooLogic = new MooLogic();
		MooDAO mooDAO = new MooDAOJdbcImpl();
		GameController controller = new GameController(ui, mooLogic, mooDAO);
		controller.run();
	}

}
