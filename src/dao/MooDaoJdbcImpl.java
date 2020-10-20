package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.PlayerAverage;

public class MooDaoJdbcImpl implements MooDao {
	static Connection connection;
	static Statement stmt;
	static ResultSet rs;
	int id = 0;
	
	public MooDaoJdbcImpl() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/moo","root","rfP5L3uaepAiKDAyZf");
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
				// TODO		Missing prompt to user when name not i db
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
	public void saveResultForUser(int userId, int score) {
		try {
			stmt.executeUpdate("INSERT INTO results " + 
					"(result, player) VALUES (" + score + ", " +	userId + ")" );
		} catch (SQLException e) {
			throw new RuntimeException("MooDaoJdbcImpl, error in saveResultForUser()");
		}
		
	}

	@Override
	public List<PlayerAverage> showTopTen() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
