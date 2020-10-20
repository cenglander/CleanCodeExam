package dao;

import model.*;
import java.util.List;

import model.PlayerAverage;

public interface MooDao {
	int getUserByName(String name);
	void saveResultForUser(int userId, int score);
	List<PlayerAverage> showTopTen();
}
