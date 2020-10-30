package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import app.Main;
import model.PlayerAverage;

public class DAOJdbcImpl implements GameDAO {
	static Connection connection;
	static Statement stmt;
	static ResultSet rs;
	int id = 0;
	Properties prop;
	static String game; 
	

	public DAOJdbcImpl(Properties prop, String GAME) {
		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost/game", "root", "rfP5L3uaepAiKDAyZf");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/game","root","root");
			stmt = connection.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException(prop.getProperty(GAME+".title") + " DAO constructor problem: " + e);
		}
		this.prop=prop;
		DAOJdbcImpl.game=GAME;
		
		
	}

	@Override
	public int getUserByName(String name) {
		try {
			rs = stmt.executeQuery("select id,name from players where name = '" + name + "'");
			if (rs.next()) {
				id = rs.getInt("id");
				return id;
			} else {
				return id;
			}
		} catch (SQLException e) {
			throw new RuntimeException("DaoJdbcImpl, error in getUserByName()");
		}
	}

	@Override
	public void saveResultForUser(int userId, int numOfGuesses) {
		try {
			stmt.executeUpdate(
					"INSERT INTO " + prop.getProperty(game + ".tableName") + "(result, player) VALUES (" + numOfGuesses + ", " + userId + ")");
		} catch (SQLException e) {
			throw new RuntimeException("DaoJdbcImpl, error in saveResultForUser()");
		}
	}

	@Override
	public List<PlayerAverage> getTopTen() {
		List<PlayerAverage> topList = new ArrayList<>();
		try {
			rs = stmt.executeQuery("select players.name, avg("+ prop.getProperty(game + ".tableName")+ ".result) as average " + "from " 
					+ prop.getProperty(game + ".tableName") + " "
					+ "join players on "+ prop.getProperty(game + ".tableName") + ".player = players.id " + "group by players.name "
					+ "order by average asc " + "limit 10");
			while (rs.next()) {
				topList.add(new PlayerAverage(rs.getString("name"), rs.getDouble("average")));
			}
		} catch (SQLException e) {
			throw new RuntimeException("DAOJdbcImpl - Error in getTopTen()");
		}
		return topList;
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public void saveResultForUser(int userId, int numOfGuesses) {
//		try {
//			stmt.executeUpdate(
//					"INSERT INTO resultsmoo " + "(result, player) VALUES (" + numOfGuesses + ", " + userId + ")");
//		} catch (SQLException e) {
//			throw new RuntimeException("MooDaoJdbcImpl, error in saveResultForUser()");
//		}
//	}
//
//	@Override
//	public List<PlayerAverage> getTopTen() {
//		List<PlayerAverage> topList = new ArrayList<>();
//		try {
//			rs = stmt.executeQuery("select players.name, avg(resultsmoo.result) as average " + "from resultsmoo "
//					+ "join players on resultsmoo.player = players.id " + "group by players.name "
//					+ "order by average asc " + "limit 10");
//			while (rs.next()) {
//				topList.add(new PlayerAverage(rs.getString("name"), rs.getDouble("average")));
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException("MooDAOJdbcImpl - Error in getTopTen()");
//		}
//		return topList;
//	}
//
//}
