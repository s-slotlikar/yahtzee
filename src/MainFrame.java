import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import javax.swing.JToggleButton;

public class MainFrame extends JFrame {
	
	//States: May use this type of design
	public final static int RESET_GAME = 0;
	public final static int BEFORE_FIRST_ROLL = 1;
	public final static int BEFORE_SECOND_ROLL = 2;
	public final static int BEFORE_THIRD_ROLL = 3;
	public final static int AFTER_THIRD_ROLL = 4;
	public final static int SCORING = 5;
	public final static int GAME_OVER = 6;
	
	//Instance Variables
	private JPanel contentPane;
	private JToggleButton[] holdButtons;
	public static final int NUM_DICE = 5; 
	public static final int NUM_SIDES = 6;
	private Dice myDice;
	private boolean[] holdArray;
	private int currentUIState;
	private JToggleButton diceButton1;
	private JToggleButton diceButton2;
	private JToggleButton diceButton3;
	private JToggleButton diceButton4;
	private JToggleButton diceButton5;
	private JButton scoreButton;
	private JButton rollButton;

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
		//Temp - should be RESET_GAME later
		manageUIState(BEFORE_FIRST_ROLL);
		
		/* Event Handlers */
		
		//Score Button 
		scoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageUIState(BEFORE_FIRST_ROLL);
			}
		});
		
		//Roll Button
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				for (int i = 0; i < NUM_DICE; i++) { 
					// If the user is not holding this die then roll
					if (!holdArray[i]) {
						myDice.rollDie(i);
						holdButtons[i].setText("" + myDice.getDieValue(i));
					}
				}
				if (currentUIState == BEFORE_FIRST_ROLL) {
					manageUIState(BEFORE_SECOND_ROLL);
				} else if (currentUIState == BEFORE_SECOND_ROLL) {
					manageUIState(BEFORE_THIRD_ROLL);
				} else if (currentUIState == BEFORE_THIRD_ROLL) {
					manageUIState(AFTER_THIRD_ROLL);
				}
			}
		});
		
		//Dice 1
		diceButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[0] = !holdArray[0];
			}
		});
		
		//Dice 2
		diceButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[1] = !holdArray[1];
			}
		});
		
		//Dice 3
		diceButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[2] = !holdArray[2];
			}
		});
		
		//Dice 4
		diceButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[3] = !holdArray[3];
			}
		});
		
		//Dice 5
		diceButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				holdArray[4] = !holdArray[4];
			}
		});
	}
	public void initComponents() {
		//Panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Dice 1
		diceButton1 = new JToggleButton("");
		diceButton1.setSize(82, 82);
		diceButton1.setLocation(new Point(10, 10));
		contentPane.setLayout(null);
		contentPane.add(diceButton1);
		
		//Dice2
		diceButton2 = new JToggleButton("");
		diceButton2.setLocation(new Point(10, 10));
		diceButton2.setBounds(103, 10, 82, 82);
		contentPane.add(diceButton2);
		
		//Dice3
		diceButton3 = new JToggleButton("");
		diceButton3.setLocation(new Point(10, 10));
		diceButton3.setBounds(195, 10, 82, 82);
		contentPane.add(diceButton3);
		
		//Dice4
		diceButton4 = new JToggleButton("");
		diceButton4.setLocation(new Point(10, 10));
		diceButton4.setBounds(287, 10, 82, 82);
		contentPane.add(diceButton4);
			
		//Dice5
		diceButton5 = new JToggleButton("");
		diceButton5.setLocation(new Point(10, 10));
		diceButton5.setBounds(379, 10, 82, 82);
		contentPane.add(diceButton5);
		
		//Score Button
		scoreButton = new JButton("Score");
		scoreButton.setBounds(379, 144, 82, 20);
		contentPane.add(scoreButton);
		
		//Roll Button
		rollButton = new JButton("Roll");
		rollButton.setBounds(195, 136, 82, 36);
		contentPane.add(rollButton);
			
		/* Initialization of Instance Variables */
				
		//Initializes the set of dices
		myDice = new Dice(NUM_DICE, NUM_SIDES);		
		//Initialize an array of buttons for each die
		holdButtons = new JToggleButton[NUM_DICE];
		holdButtons[0] = diceButton1;
		holdButtons[1] = diceButton2;
		holdButtons[2] = diceButton3;
		holdButtons[3] = diceButton4;
		holdButtons[4] = diceButton5;
		holdArray = new boolean[NUM_DICE];
	}
	
	//Util Methods
	public void manageUIState(int nextState) {
		switch(nextState) {
			case RESET_GAME:
				break; 
			case BEFORE_FIRST_ROLL:
				System.out.println("CASE 1");
				//Disable the roll buttons
				clearAndDisableHoldButtons();
				
				//Enable Roll Button
				rollButton.setEnabled(true);
			
				//Clear the hold array
				clearHoldArray();
				break;
			case BEFORE_SECOND_ROLL:
				//Enables the hold buttons after rolling the dice once
				System.out.println("CASE 2");
				enableHoldButtons();
				break;
			case BEFORE_THIRD_ROLL:
				System.out.println("CASE 3");
				break;
			case AFTER_THIRD_ROLL:
				System.out.println("CASE 4");
				//Disable the Roll Button
				rollButton.setEnabled(false);
			case SCORING:
				System.out.println("CASE 5");
				break;
			case GAME_OVER:
				System.out.println("CASE 6");
				break;
			default:
				System.out.println("CASE 7");
				JOptionPane.showMessageDialog(this, "Invalid UI state detected");
				break;
		}
		currentUIState = nextState;
	}
	
	//Helper Methods
	private void clearAndDisableHoldButtons() {
		for (int i = 0; i < holdButtons.length; i++) {
			System.out.println("disabled button");
			holdButtons[i].setText("");
			holdButtons[i].setEnabled(false);
			holdButtons[i].setSelected(false);
		}
	}
	
	private void clearHoldArray() {
		for (int i = 0; i < holdArray.length; i++) {
			System.out.println("cleared hold array");
			holdArray[i] = false;
		}
	}
	
	private void enableHoldButtons() {
		System.out.println("enabled button");
		for (int i = 0; i < holdButtons.length; i++) {
			holdButtons[i].setEnabled(true);
		}
	}
}
