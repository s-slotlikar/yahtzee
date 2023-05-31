
public class Main {

	public static void main(String[] args) {
		//Test Die
		Die testDie = new Die(6);
		
		System.out.println("The die rolls a " + testDie.roll());
		
		//Test Dice
		Dice testDice = new Dice(5, 6);
		System.out.println("Sum of 5 six-sided dice is " + testDice.roll());
		
	}

}
