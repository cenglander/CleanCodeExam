package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.PlayerAverage;

public class MasterMindDAOJdbcImpl implements GameDAO {
	static Connection connection;
	static Statement stmt;
	static ResultSet rs;
	int id = 0;

	public MasterMindDAOJdbcImpl() {
		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost/game", "root", "rfP5L3uaepAiKDAyZf");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/game", "root", "root");
			stmt = connection.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException("MasterMind DAO constructor problem: " + e);
		}
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
			throw new RuntimeException("MasterMindDaoJdbcImpl, error in getUserByName()");
		}
	}

	@Override
	public void saveResultForUser(int userId, int numOfGuesses) {
		try {
			stmt.executeUpdate("INSERT INTO resultsmastermind " + "(result, player) VALUES (" + numOfGuesses + ", "
					+ userId + ")");
		} catch (SQLException e) {
			throw new RuntimeException("MasterMindDaoJdbcImpl, error in saveResultForUser()");
		}
	}

	@Override
	public List<PlayerAverage> getTopTen() {
		List<PlayerAverage> topList = new ArrayList<>();
		try {
			rs = stmt.executeQuery("select players.name, avg(resultsmastermind.result) as average "
					+ "from resultsmastermind " + "join players on resultsmastermind.player = players.id "
					+ "group by players.name " + "order by average asc " + "limit 10");
			while (rs.next()) {
				topList.add(new PlayerAverage(rs.getString("name"), rs.getDouble("average")));
			}
		} catch (SQLException e) {
			throw new RuntimeException("MasterMindDAOJdbcImpl - Error in getTopTen()");
		}
		return topList;
	}

}
