/**
 * General Purpose Die
 * @author SnickerSatisfy	
 *
 */
public class Die {
	private int numSides; //stores the number of sides on the die
	private int value; // Holds the current value of the die
	
	//Default Constructor
	public Die() {
		setNumSides(0);
		setValue(0);
	}
	
	public Die(int sides) {
		setNumSides(sides);
		setValue(0); //Initial value is always 0 because nothing is rolled yet
	}
	
	/**
	 * 
	 * @return a random integer from [0, numSides]
	 */
	public int roll() {
		setValue((int)(Math.random() * getNumSides()) + 1);
		return getValue();
	}
	
	
	//Encapsulation
	public int getNumSides() {
		return numSides;
	}

	public void setNumSides(int numSides) {
		this.numSides = numSides;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
