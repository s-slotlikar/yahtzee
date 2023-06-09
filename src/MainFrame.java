import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.TextField;

import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	
	//UI State Machine Constants
	public final static int RESET_GAME = 0;
	public final static int BEFORE_FIRST_ROLL = 1;
	public final static int BEFORE_SECOND_ROLL = 2;
	public final static int BEFORE_THIRD_ROLL = 3;
	public final static int AFTER_THIRD_ROLL = 4;
	public final static int SCORING = 5;
	public final static int GAME_OVER = 6;
	
	// Lower Score 
	public static final int THREE_OF_A_KIND = 0;
	public static final int FOUR_OF_A_KIND = 1;
	public static final int FULL_HOUSE = 2;
	public static final int SMALL_STRAIGHT = 3;
	public static final int LARGE_STRAIGHT = 4;
	public static final int YAHTZEE = 5;
	public static final int CHANCE = 6;
	
	//Global Constants
	public static final int NUM_DICE = 5; 
	public static final int NUM_SIDES = 6;
	
	//Instance Variables
	private Dice myDice;
	private GameModel game;
	private int currentUIState;
	private int rollNum; //Temp
	private JToggleButton[] diceToggleButtons;
	private boolean[] holdArray;
	private JToggleButton[] upperScoreButtonArray;
	private JToggleButton[] lowerScoreButtonArray;
	private JTextField[] upperScoreTextBoxArray;
	private JTextField[] lowerScoreTextBoxArray;
	
	//Components
	private JPanel contentPane;
	private JToggleButton diceToggle1;
	private JToggleButton diceToggle2;
	private JToggleButton diceToggle3;
	private JToggleButton diceToggle4;
	private JToggleButton diceToggle6;
	private JButton rollButton;
	private JTextField aceTextField;
	private JTextField twosTextField;
	private JTextField threesTextField;
	private JTextField foursTextField;
	private JTextField fivesTextField;
	private JTextField sixesTextField;
	private JTextField threeOfAKindTextField;
	private JTextField fourOfAKindTextField;
	private JTextField fullHouseTextField;
	private JTextField smallStraightTextField;
	private JTextField largeStraightTextField;
	private JTextField yahtzeeTextField;
	private JTextField chanceTextField;
	private JTextField bonusTextField;
	private JTextField upperScoreTextField;
	private JTextField lowerScoreTextField;
	private JTextField grandTotalTextField;
	private JToggleButton aceToggle;
	private JToggleButton twosToggle;
	private JToggleButton threesToggle;
	private JToggleButton foursToggle;
	private JToggleButton fivesToggle;
	private JToggleButton sixesToggle;
	private JToggleButton threeOfAKindToggle;
	private JToggleButton fourOfAKindToggle;
	private JToggleButton fullHouseToggle;
	private JToggleButton smallStraightToggle;
	private JToggleButton largeStraightToggle;
	private JToggleButton yahtzeeToggle;
	private JToggleButton chanceToggle;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		/* UI Components */
		initComponents();
		/* Initialization of Instance Variables */
		
		//Initializes the set of dices
		myDice = new Dice(NUM_DICE, NUM_SIDES);		
		//Initialize an array of buttons for each die
		diceToggleButtons = new JToggleButton[NUM_DICE];
		diceToggleButtons[0] = diceToggle1;
		diceToggleButtons[1] = diceToggle2;
		diceToggleButtons[2] = diceToggle3;
		diceToggleButtons[3] = diceToggle4;
		diceToggleButtons[4] = diceToggle6;
		
		upperScoreButtonArray = new JToggleButton[GameModel.NUM_UPPER_SCORE_CATS + 1];
		upperScoreButtonArray[1] = this.aceToggle;
		upperScoreButtonArray[2] = this.twosToggle;
		upperScoreButtonArray[3] = this.threesToggle;
		upperScoreButtonArray[4] = this.foursToggle;
		upperScoreButtonArray[5] = this.fivesToggle;
		upperScoreButtonArray[6] = this.sixesToggle;
		
		lowerScoreButtonArray = new JToggleButton[GameModel.NUM_LOWER_SCORE_CATS];
		lowerScoreButtonArray[THREE_OF_A_KIND] = this.threeOfAKindToggle;
		
		lowerScoreTextBoxArray = new JTextField[GameModel.NUM_LOWER_SCORE_CATS];
		lowerScoreTextBoxArray[THREE_OF_A_KIND] = this.threeOfAKindTextField;
		
		
		holdArray = new boolean[NUM_DICE];
		
		game = new GameModel();
		
		
		manageUIState(RESET_GAME);
		
		//Temp - should be RESET_GAME later
		manageUIState(BEFORE_FIRST_ROLL);
		rollNum = 1;
		
		//Roll Button
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rollValue;
				
				for (int i = 0; i < NUM_DICE; i++) { 
					
					// If the user is not holding this die then roll
					if (!holdArray[i]) {
						
						rollValue = myDice.rollDie(i);
						diceToggleButtons[i].setText("" + rollValue);
						
					}
				}
				rollNum++;
				
				switch(currentUIState) {
				case BEFORE_FIRST_ROLL:
					
					manageUIState(BEFORE_SECOND_ROLL);
					
					break;
				case BEFORE_SECOND_ROLL:
					
					manageUIState(BEFORE_THIRD_ROLL);
					
					break;
				case BEFORE_THIRD_ROLL:
					
					manageUIState(AFTER_THIRD_ROLL);
					
					break;
				}
				
				if (rollNum > 3) {
					rollButton.setEnabled(true);
				}
			}
		});
		
		//Dice 1
		diceToggle1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[0] = !holdArray[0];
			}
		});
		
		//Dice 2
		diceToggle2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[1] = !holdArray[1];
			}
		});
		
		//Dice 3
		diceToggle3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[2] = !holdArray[2];
			}
		});
		
		//Dice 4
		diceToggle4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[3] = !holdArray[3];
			}
		});
		
		//Dice 5
		diceToggle6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[4] = !holdArray[4];
			}
		});
	}
	
	//Util Methods
	public void manageUIState(int nextState) {
		switch(nextState) {
			case RESET_GAME:
				break; 
			case BEFORE_FIRST_ROLL:
				//Temp
				rollButton.setEnabled(true);
				
				//Disable the roll buttons
				clearAndDisableHoldButtons();
				
				//Enable Roll Button
				rollButton.setEnabled(true);
			
				//Clear the hold array
				clearHoldArray();
				
				break;
			case BEFORE_SECOND_ROLL:
				//Enables the hold buttons after rolling the dice once
				setHoldButtonsEnableState(true);
				
				break;
			case BEFORE_THIRD_ROLL:
				
				break;
			case AFTER_THIRD_ROLL:
				//Disable the Roll Button
				setHoldButtonsEnableState(false);
				rollButton.setEnabled(false);
				
				break;
			case SCORING:
				
				break;
			case GAME_OVER:
				
				break;
			default:
				JOptionPane.showMessageDialog(this, "Invalid UI state detected");
				
				break;
		}
		currentUIState = nextState;
	}
	
	//Helper Methods
	private void clearAndDisableHoldButtons() {
		for (int i = 0; i < diceToggleButtons.length; i++) {
			System.out.println("disabled button");
			diceToggleButtons[i].setText("");
			diceToggleButtons[i].setEnabled(false);
			diceToggleButtons[i].setSelected(false);
		}
	}
	
	private void clearHoldArray() {
		for (int i = 0; i < holdArray.length; i++) {
			System.out.println("cleared hold array");
			holdArray[i] = false;
		}
	}
	
	private void setHoldButtonsEnableState(boolean isEnabled) {
		System.out.println("enabled button");
		for (int i = 0; i < diceToggleButtons.length; i++) {
			diceToggleButtons[i].setEnabled(isEnabled);
		}
	}
	
	//All the UI Components setup here
	public void initComponents() {
		//Panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Dice 1
		diceToggle1 = new JToggleButton("");
		diceToggle1.setSize(82, 82);
		diceToggle1.setLocation(new Point(10, 10));
		contentPane.setLayout(null);
		contentPane.add(diceToggle1);
		
		//Dice2
		diceToggle2 = new JToggleButton("");
		diceToggle2.setLocation(new Point(10, 10));
		diceToggle2.setBounds(110, 10, 82, 82);
		contentPane.add(diceToggle2);
		
		//Dice3
		diceToggle3 = new JToggleButton("");
		diceToggle3.setLocation(new Point(10, 10));
		diceToggle3.setBounds(210, 10, 82, 82);
		contentPane.add(diceToggle3);
		
		//Dice4
		diceToggle4 = new JToggleButton("");
		diceToggle4.setLocation(new Point(10, 10));
		diceToggle4.setBounds(310, 10, 82, 82);
		contentPane.add(diceToggle4);
			
		//Dice5
		diceToggle6 = new JToggleButton("");
		diceToggle6.setLocation(new Point(10, 10));
		diceToggle6.setBounds(410, 10, 82, 82);
		contentPane.add(diceToggle6);
		
		//Roll Button
		rollButton = new JButton("Roll");
		rollButton.setBounds(181, 136, 96, 42);
		contentPane.add(rollButton);
		
		aceToggle = new JToggleButton("Ace");
		aceToggle.setBounds(10, 206, 82, 34);
		contentPane.add(aceToggle);
		
		twosToggle = new JToggleButton("Twos");
		twosToggle.setBounds(10, 251, 82, 34);
		contentPane.add(twosToggle);
		
		threesToggle = new JToggleButton("Threes");
		threesToggle.setBounds(10, 296, 82, 34);
		contentPane.add(threesToggle);
		
		foursToggle = new JToggleButton("Fours");
		foursToggle.setBounds(10, 341, 82, 34);
		contentPane.add(foursToggle);
		
		fivesToggle = new JToggleButton("Fives");
		fivesToggle.setBounds(10, 386, 82, 34);
		contentPane.add(fivesToggle);
		
		sixesToggle = new JToggleButton("Sixes");
		sixesToggle.setBounds(10, 431, 82, 34);
		contentPane.add(sixesToggle);
		
		threeOfAKindToggle = new JToggleButton("3 of a Kind");
		threeOfAKindToggle.setBounds(236, 206, 119, 34);
		contentPane.add(threeOfAKindToggle);
		
		fourOfAKindToggle = new JToggleButton("4 of a Kind");
		fourOfAKindToggle.setBounds(236, 251, 119, 34);
		contentPane.add(fourOfAKindToggle);
		
		fullHouseToggle = new JToggleButton("Full House");
		fullHouseToggle.setBounds(236, 296, 119, 34);
		contentPane.add(fullHouseToggle);
		
		smallStraightToggle = new JToggleButton("Small Straight");
		smallStraightToggle.setBounds(236, 341, 119, 34);
		contentPane.add(smallStraightToggle);
		
		largeStraightToggle = new JToggleButton("Large Straight");
		largeStraightToggle.setBounds(236, 386, 119, 34);
		contentPane.add(largeStraightToggle);
		
		yahtzeeToggle = new JToggleButton("YAHTZEE");
		yahtzeeToggle.setBounds(236, 431, 119, 34);
		contentPane.add(yahtzeeToggle);
		
		chanceToggle = new JToggleButton("Chance");
		chanceToggle.setBounds(236, 475, 119, 34);
		contentPane.add(chanceToggle);
		
		aceTextField = new JTextField();
		aceTextField.setBounds(132, 214, 53, 19);
		contentPane.add(aceTextField);
		aceTextField.setColumns(10);
		
		twosTextField = new JTextField();
		twosTextField.setBounds(132, 259, 53, 19);
		contentPane.add(twosTextField);
		twosTextField.setColumns(10);
		
		threesTextField = new JTextField();
		threesTextField.setColumns(10);
		threesTextField.setBounds(132, 304, 53, 19);
		contentPane.add(threesTextField);
		
		foursTextField = new JTextField();
		foursTextField.setColumns(10);
		foursTextField.setBounds(132, 349, 53, 19);
		contentPane.add(foursTextField);
		
		fivesTextField = new JTextField();
		fivesTextField.setColumns(10);
		fivesTextField.setBounds(132, 394, 53, 19);
		contentPane.add(fivesTextField);
		
		sixesTextField = new JTextField();
		sixesTextField.setColumns(10);
		sixesTextField.setBounds(132, 439, 53, 19);
		contentPane.add(sixesTextField);
		
		threeOfAKindTextField = new JTextField();
		threeOfAKindTextField.setColumns(10);
		threeOfAKindTextField.setBounds(379, 214, 59, 19);
		contentPane.add(threeOfAKindTextField);
		
		fourOfAKindTextField = new JTextField();
		fourOfAKindTextField.setColumns(10);
		fourOfAKindTextField.setBounds(379, 259, 59, 19);
		contentPane.add(fourOfAKindTextField);
		
		fullHouseTextField = new JTextField();
		fullHouseTextField.setColumns(10);
		fullHouseTextField.setBounds(379, 304, 59, 19);
		contentPane.add(fullHouseTextField);
		
		smallStraightTextField = new JTextField();
		smallStraightTextField.setColumns(10);
		smallStraightTextField.setBounds(379, 349, 59, 19);
		contentPane.add(smallStraightTextField);
		
		largeStraightTextField = new JTextField();
		largeStraightTextField.setColumns(10);
		largeStraightTextField.setBounds(379, 394, 59, 19);
		contentPane.add(largeStraightTextField);
		
		yahtzeeTextField = new JTextField();
		yahtzeeTextField.setColumns(10);
		yahtzeeTextField.setBounds(379, 439, 59, 19);
		contentPane.add(yahtzeeTextField);
		
		chanceTextField = new JTextField();
		chanceTextField.setColumns(10);
		chanceTextField.setBounds(379, 483, 59, 19);
		contentPane.add(chanceTextField);
		
		bonusTextField = new JTextField();
		bonusTextField.setColumns(10);
		bonusTextField.setBounds(132, 477, 53, 19);
		contentPane.add(bonusTextField);
		
		upperScoreTextField = new JTextField();
		upperScoreTextField.setColumns(10);
		upperScoreTextField.setBounds(132, 505, 53, 19);
		contentPane.add(upperScoreTextField);
		
		lowerScoreTextField = new JTextField();
		lowerScoreTextField.setColumns(10);
		lowerScoreTextField.setBounds(379, 522, 59, 19);
		contentPane.add(lowerScoreTextField);
		
		grandTotalTextField = new JTextField();
		grandTotalTextField.setColumns(10);
		grandTotalTextField.setBounds(379, 551, 59, 19);
		contentPane.add(grandTotalTextField);
		
		JLabel bonusLabel = new JLabel("Bonus if > 63");
		bonusLabel.setBounds(20, 475, 87, 23);
		contentPane.add(bonusLabel);
		
		JLabel totalUpperScoreLabel = new JLabel("Upper Score");
		totalUpperScoreLabel.setBounds(20, 503, 127, 23);
		contentPane.add(totalUpperScoreLabel);
		
		JLabel lowerScoreLabel = new JLabel("Lower Score");
		lowerScoreLabel.setBounds(267, 528, 102, 13);
		contentPane.add(lowerScoreLabel);
		
		JLabel grandTotalLabel = new JLabel("Grand Total");
		grandTotalLabel.setBounds(267, 557, 102, 13);
		contentPane.add(grandTotalLabel);
		holdArray = new boolean[NUM_DICE];
	}
}
