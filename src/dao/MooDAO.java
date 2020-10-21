package dao;


import java.util.List;
import model.PlayerAverage;

public interface MooDAO {
	int getUserByName(String name);
	void saveResultForUser(int userId, int score);
	List<PlayerAverage> getTopTen();
}
