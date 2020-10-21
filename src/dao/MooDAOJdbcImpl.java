package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.PlayerAverage;

public class MooDAOJdbcImpl implements MooDAO {
	static Connection connection;
	static Statement stmt;
	static ResultSet rs;
	int id = 0;
	
	public MooDAOJdbcImpl() {
		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost/moo","root","rfP5L3uaepAiKDAyZf");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/moo","root","root");
			stmt = connection.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException("MooDAO constructor problem: " + e);
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
				// TODO		Missing prompt to user when name not in db
//			gw.addString("User not in database, please register with admin");
//				Thread.sleep(5000);
//			gw.exit();
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("MooDaoJdbcImpl, error in getUserByName()");
		}
	}

	@Override
	public void saveResultForUser(int userId, int numOfGuesses) {
		try {
			stmt.executeUpdate("INSERT INTO results " + 
					"(result, player) VALUES (" + numOfGuesses + ", " +	userId + ")" );
		} catch (SQLException e) {
			throw new RuntimeException("MooDaoJdbcImpl, error in saveResultForUser()");
		}
	}

	@Override
	public List<PlayerAverage> getTopTen() {
		// TODO Auto-generated method stub
		List<PlayerAverage> topList = new ArrayList<>();
		try {
			rs = stmt.executeQuery("select players.name, avg(results.result) as average " + 
					"from results " + 
					"join players on results.player = players.id " + 
					"group by players.name " + 
					"order by average asc " + 
					"limit 10");
			while(rs.next()) {
				topList.add(new PlayerAverage(rs.getString("name"), rs.getDouble("average")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("MooDAOJdbcImpl - Error in getTopTen()");
		}
		return topList;
	}
	
}
