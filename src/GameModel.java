
/**
 * Purpose of this class is to separate the process code and the game itself.
 *
 *
 */
public class GameModel {
	
	//Game Model Constants
	public static final int BONUS = 35; // This is how much score you get if you reach the score you need to get the bonus
	public static final int NUM_UPPER_SCORE_CATS = 6; 
	public static final int NUM_LOWER_SCORE_CATS = 7;
	public static final int MAX_NUM_TURNS = NUM_UPPER_SCORE_CATS + NUM_LOWER_SCORE_CATS;
	
	//Instance Variables
	private int[] upperScoreCategories;
	private int[] lowerScoreCategories;
	private boolean[] usedUpperScoreCategories;
	private boolean[] usedLowerScoreCategories;
	private int currentTurnNum;
	private int sumUpperScores;
	private int sumLowerScores;
	private int grandTotal;
	private int bonus;
	
	public GameModel() {
		upperScoreCategories = new int[NUM_UPPER_SCORE_CATS  + 1];
		lowerScoreCategories = new int[NUM_LOWER_SCORE_CATS];
		
		usedUpperScoreCategories = new boolean[NUM_UPPER_SCORE_CATS + 1];
		usedLowerScoreCategories = new boolean[NUM_LOWER_SCORE_CATS];
		
		currentTurnNum = 1;
		
		bonus = 0;
		sumUpperScores = 0;
		sumLowerScores = 0;
		grandTotal = 0;
	}

	//Lower Score Combinations
	public boolean isThreeOfAKind(Dice myDice) {
		int[] freqTable = myDice.buildFrequencyTable();
		
		boolean foundThreeOfAKind = false;
		
		for (int i = 1; i <= myDice.getNumSides(); i++) {
			if (freqTable[i] >= 3) {
				foundThreeOfAKind = true;
			}
		}
		
		return foundThreeOfAKind;
	}
	public boolean isFourOfAKind(Dice myDice) {
		int[] freqTable;
		freqTable = myDice.buildFrequencyTable();
		
		boolean foundFourOfAKind = false;
		
		for (int i = 1; i <= myDice.getNumSides(); i++) {
			if (freqTable[i] >= 4) {
				foundFourOfAKind = true;
			}
		}
		
		return foundFourOfAKind;
	}
	public boolean isFullHouse(Dice myDice){
		int[] freqTable = myDice.buildFrequencyTable();
		
		boolean foundFreqOfThree = false;
		boolean foundFreqOfTwo = false;
		
		for (int i = 1; i <= myDice.getNumSides(); i++) {
			if (freqTable[i] == 3) {
				foundFreqOfThree = true;
			} else if (freqTable[i] == 2) {
				foundFreqOfTwo = true;
			}
		}
		
		return foundFreqOfThree && foundFreqOfTwo;
		
	}
	public boolean isSmallStraight(Dice myDice) {
		int[] freqTable = myDice.buildFrequencyTable();
		
		if (freqTable[3] >= 1 && freqTable[4] >= 1) {
			if (freqTable[1] >= 1 && freqTable[2] >= 1) {
				return true;
			} else if (freqTable[2] >= 1 && freqTable[5] >= 1) {
				return true;
			} else if (freqTable[5] >= 1 && freqTable[6] >= 1) {
				return true;
			}
		}
		
		
		return false;
	}
	public boolean isLargeStraight(Dice myDice) {
		int[] freqTable = myDice.buildFrequencyTable();
		
		for (int i = 1; i <= myDice.getNumSides(); i++) {
			if (freqTable[i] > 1) {
				return false;
			}
		}
		
		return true;
	}
	public boolean isYahtzee(Dice myDice) {
		int[] freqTable = myDice.buildFrequencyTable();
		
		boolean foundYahtzee = false;
		
		for (int i = 1; i <= myDice.getNumSides(); i++) {
			if (freqTable[i] == 5) {
				foundYahtzee = true;
			}
		}
		return foundYahtzee;
	}
	
	//Score
	public int addScore(Dice myDice) {
		int sum = 0;
		
		for (int i = 0; i < myDice.getNumDice(); i++) {
			sum += myDice.getDieValue(i);
		}
		
		return sum;
	}
	
	//Update Total
	public void updateTotals() {
		bonus = 0;
		sumUpperScores = 0;
		sumLowerScores = 0;
		grandTotal = 0;
		
		//Upper Score Cats
		for (int i = 1; i <= NUM_UPPER_SCORE_CATS; i++) {
			sumUpperScores += upperScoreCategories[i];
		}
		
		if (getSumUpperScores() >= 63) {
			bonus = 35;
		}
		sumUpperScores += bonus;
		
		//Lower Score Cats
		for (int i = 0; i < NUM_LOWER_SCORE_CATS; i++) {
			sumLowerScores += lowerScoreCategories[i];
		}
		
		grandTotal = sumUpperScores + sumLowerScores;
	}
	
	/* HELPER METHODS*/
	//Used Cats
	public void clearUsedScoringCats() {
		for (int i = 1; i <= NUM_UPPER_SCORE_CATS; i++) {
			usedUpperScoreCategories[i] = false;
		}
		
		for (int i = 0; i < NUM_LOWER_SCORE_CATS; i++) {
			usedLowerScoreCategories[i] = false;
		}
	}
	
	//Turn Number
	public int getCurrentTurnNum() {
		return currentTurnNum;
	}
	public void nextTurn() {
		currentTurnNum++;
	}
	public void resetTurnNum() {
		currentTurnNum = 1;
	}

	//Lower Cats
	public void clearAllLowerScoringCats() {
		for (int i = 0; i < NUM_LOWER_SCORE_CATS; i++) {
			lowerScoreCategories[i] = 0;
		}
	}	
	public void setLowerScoreCat(int index, int score) {
		lowerScoreCategories[index] = score;
	}
	public void setUsedLowerScoreCat(int index, boolean used) {
		usedLowerScoreCategories[index] = used	;
	}
	public boolean getUsedLowerScoringCatState(int index) {
		return usedLowerScoreCategories[index];
	}
	
	//Upper Cats
	public void clearAllUpperScoringCats() {
		for (int i = 1; i <= NUM_UPPER_SCORE_CATS; i++) {
			upperScoreCategories[i] = 0;
		}
	}
	public void setUpperScoreCat(int index, int score) {
		upperScoreCategories[index] = score;
	}
	public void setUsedUpperScoreCat(int index, boolean used) {
		usedUpperScoreCategories[index] = used	;
	}
	public boolean getUsedUpperScoringCatState(int index) {
		return usedUpperScoreCategories[index];
	}

	
	//Encapsulation
	public int getSumUpperScores() {
		return sumUpperScores;
	}
	public int getSumLowerScores() {
		return sumLowerScores;
	}
	public int getGrandTotal() {
		return grandTotal;
	}

	public int getBonus() {
		return bonus;
	}

}
