package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import controller.GameController;
import dao.GameDAO;
import dao.DAOJdbcImpl;
import model.GameLogic;
import model.MasterMindLogic;
import model.MooLogic;
import view.SimpleWindow;
import view.Ui;

public class Main {

	final static String GAME = "moo";  
//	final static String GAME = "mastermind";

	public static void main(String[] args) {
		try (InputStream input = new FileInputStream("src/app/resources/config.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			Ui ui = new SimpleWindow(prop.getProperty(GAME + ".title"));
			
//			GameLogic gameLogic = new  MasterMindLogic(); 
			GameLogic gameLogic = new  MooLogic(); 
			GameDAO gameDAO = new DAOJdbcImpl(prop, GAME);
			GameController controller = new GameController(ui, gameLogic, gameDAO);
			controller.run();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
