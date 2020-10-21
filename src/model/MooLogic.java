package model;

public class MooLogic {

//	public static String generateAnswerKey(){
//		String answerKey = "";
//		for (int i = 0; i < 4; i++){
//			int random = (int) (Math.random() * 10);
//			String randomDigit = String.valueOf(random);
//			while (answerKey.contains(randomDigit)){
//				random = (int) (Math.random() * 10);
//				randomDigit = String.valueOf(random);
//			}
//			answerKey = answerKey + randomDigit;
//		}
//		return answerKey;
//	}

	public String generateAnswerKey(){
	String answerKey = "";
	String randomDigit="";
	int randomNum=0;
	for (int i = 0; i < 4; i++){	
		do {
			randomNum = (int) (Math.random() * 10);
			randomDigit = String.valueOf(randomNum);
		} while (answerKey.contains(randomDigit));
		answerKey = answerKey + randomDigit;
	}
	return answerKey;
}
	
	
	public String checkBullsCows(String goal, String guess) {
		int cows = 0, bulls = 0;
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++ ) {
				if (goal.charAt(i) == guess.charAt(j)){
					if (i == j) {
						bulls++;
					} else {
						cows++;
					}
				}
			}
		}
		String result = "";
		for (int i = 0; i < bulls; i++){
			result = result + "B";
		}
		result = result + ",";
		for (int i = 0; i < cows; i++){
			result = result + "C";
		}
		return result;
	
	}
	
}
