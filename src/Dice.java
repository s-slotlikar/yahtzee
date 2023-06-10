/**
 * A general purpose set of dice
 * @author SnickerSatisfy
 *
 */
public class Dice {
	private int numDice; // Holds the number of dices
	private int numSides; // Holds the number of sides in each die
	private Die[] dices; // Contains the dices needed for the game
	
	/**
	 * Default Constructor. Creates an empty set of Dice
	 */
	public Dice() {
		setNumDice(0);
		setNumSides(0);
		dices = null; 
	}
	
	/**
	 * Constructs a new set of dice with a certain number of sides
	 * @param numDice The number of individual die in the set
	 * @param numSides The number of sides on each individual die
	 */
	public Dice(int numDice, int numSides) {
		this.setNumDice(numDice);
		this.setNumSides(numSides);
		this.dices = new Die[numDice];
		
		//Initialize each die in the array
		for (int i = 0; i < numDice; i++) {
			dices[i] = new Die(numSides);
		}
	}
	
	/**
	 * Rolls the set of dice
	 * @return The sum of all the dice
	 */
	public int roll() {
		int total = 0;
		
		//Roll each die and add the value
		for (int i = 0; i < dices.length; i++) {
			total += rollDie(i);
		}
		
		return total;
	}
	
	/**
	 * Rolls a single die in the set of dice
	 * @param dieNum Indicates which die to roll
	 * @return The value of the die
	 */
	public int rollDie(int dieNum) {
		return dices[dieNum].roll();
	}
	
	/**
	 * Iterates through the set of dices and creates a frequency table
	 * @return A frequency table which gets the count for each occurrence in the user's set of dices
	 */
	public int[] buildFrequencyTable() {
		int[] freqTable = new int[getNumSides() + 1];
		
		for (int i = 0; i < getNumDice(); i++) {
			freqTable[dices[i].getValue()]++;
		}
		
		return freqTable;
	}
	
	/**
	 * Obtains the value of a single die in the array
	 * @param dieNum Indicate which die to get 
	 * @return The value of the die 
	 */
	public int getDieValue(int dieNum) {
		return dices[dieNum].getValue();
	}
	
	/**
	 * Gets the sum of the dice without rolling
	 * @return
	 */
	public int addScore() {
		int total = 0;
		
		for (int i = 0; i < dices.length; i++) {
			total += getDieValue(i);
		}
		
		return total;
	}

	
	//Encapsulation
	public int getNumDice() {
		return numDice;
	}

	public void setNumDice(int numDice) {
		this.numDice = numDice;
	}

	public int getNumSides() {
		return numSides;
	}

	public void setNumSides(int numSides) {
		this.numSides = numSides;
	}
	
}
