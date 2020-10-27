package model.common;

public class RandomGenerator {
	
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
}