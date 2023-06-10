
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
		int[] freqTable;
		freqTable = myDice.buildFrequencyTable();
		
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
		return false;
	}
	public boolean isSmallStraight(Dice myDice) {
		return false;
	}
	public boolean isLargeStraight(Dice myDice) {
		return false;
	}
	public boolean isYahtzee(Dice myDice) {
		return false;
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
	
	//Lower Cats
	public void clearAllLowerScoreCats() {
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
	public void clearAllUpperScoreCats() {
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
