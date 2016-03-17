import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MortgageView extends JFrame {

	// field width constant
	private final int FIELD_WIDTH = 15;
	private final int FRAME_WIDTH = 250;
	private final int FRAME_HEIGHT = 240;
	private final int LOCATION = 200;
	
	// calculator fields
	private JTextField firstField = new JTextField(FIELD_WIDTH);
	private JTextField secondField = new JTextField(FIELD_WIDTH);
	private JTextField thirdField = new JTextField(FIELD_WIDTH);
	private JTextField fourthField = new JTextField(FIELD_WIDTH);
	private JButton calculateButton = new JButton();
	
	// result labels
	private JLabel resultOne = new JLabel();
	private JLabel resultTwo = new JLabel();
	private JLabel resultThree = new JLabel();
	private JLabel resultFour = new JLabel();
	private JLabel resultFive = new JLabel();
	private JLabel resultSix = new JLabel();
	private JLabel resultSeven = new JLabel();
	
	// check boxes
	private JLabel checkTitle = new JLabel("Choose Payment Schedule");
	private JCheckBox checkOne = new JCheckBox("Monthly");
	private JCheckBox checkTwo = new JCheckBox("Daily");
	private JCheckBox checkThree = new JCheckBox("Weekly");
	
	// panel
	private JPanel myPanel;
	// button group
	private ButtonGroup buttonGroup;
	
	/**
	 * 
	 * constructor that sets up initial view
	 * 
	 */
	public MortgageView()
	{	
		super("Mortgage Calculator");
		myPanel = new JPanel();
		buttonGroup = new ButtonGroup();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocation(LOCATION,LOCATION);
		Container container = getContentPane();
		container.setLayout(new GridLayout());
		// show calculator as initial view
		showCalculator();
	}
	
	/**
	 * 
	 * get contents of input fields
	 * 
	 * @return int
	 */
	
	public double getFirstNumber()
	{
		return Double.parseDouble(firstField.getText());
	}
	
	public double getSecondNumber()
	{
		return Double.parseDouble(secondField.getText());
	}
	
	public double getThirdNumber()
	{
		return Double.parseDouble(thirdField.getText());
	}
	
	public double getFourthNumber()
	{
		return Double.parseDouble(fourthField.getText());
	}
	
	/**
	 * 
	 * Show/hide calculator
	 * 
	 */
	
	
	public void showCalculator()
	{
		setTextFieldValues();
		setCalcButton("Calculate");
		myPanel.add(firstField);
		myPanel.add(secondField);
		myPanel.add(thirdField);
		myPanel.add(fourthField);
		setUpButtonGroup();
		myPanel.add(calculateButton);
		refreshFrame();
	}
	
	public void hideCalculator()
	{
		setCalcButton("Reset");
		myPanel.remove(firstField);
		myPanel.remove(secondField);
		myPanel.remove(thirdField);
		myPanel.remove(fourthField);
		myPanel.remove(checkTitle);
		myPanel.remove(checkOne);
		myPanel.remove(checkTwo);
		myPanel.remove(checkThree);
		refreshFrame();
	}
	
	/**
	 * 
	 * Show/hide results
	 * 
	 */
	
	public void showResults()
	{
		setCalcButton("Reset");
		myPanel.add(resultOne);
		myPanel.add(resultTwo);
		myPanel.add(resultThree);
		myPanel.add(resultFour);
		myPanel.add(resultFive);
		myPanel.add(resultSix);
		myPanel.add(resultSeven);
		refreshFrame();
	}
	
	public void hideResults()
	{
		myPanel.remove(resultOne);
		myPanel.remove(resultTwo);
		myPanel.remove(resultThree);
		myPanel.remove(resultFour);
		myPanel.remove(resultFive);
		myPanel.remove(resultSix);
		myPanel.remove(resultSeven);
		refreshFrame();
	}
	
	/**
	 * 
	 * set the text field of each result label
	 * 
	 * @param result
	 */
	
	public void setResultOne(double result)
	{
		resultOne.setText("Blended Monthly Payment: $" + Double.toString(result));
	}
	
	public void setResultTwo(double result)
	{
		resultTwo.setText("Total Interest Paid: $" + Double.toString(result));
	}
	
	public void setResultThree(double result)
	{
		resultThree.setText("Total Interest and Principle: $" + Double.toString(result));
	}
	
	public void setResultFour(double result)
	{
		resultFour.setText("Interest/Principle Ratio: " + Double.toString(result));
	}
	
	public void setResultFive(double result)
	{
		resultFive.setText("Average Interest per Year: $" + Double.toString(result));
	}
	
	public void setResultSix(double result)
	{
		resultSix.setText("Average Interest per Month: $" + Double.toString(result));
	}
	
	public void setResultSeven(double result)
	{
		resultSeven.setText("Ammortization, in Years: " + Double.toString(result));
	}
	
	/**
	 * 
	 * helper methods
	 * 
	 * set main button text value
	 * refreshing the JFrame
	 * setting input field default values
	 * 
	 * @return int
	 */

	public void setCalcButton(String string)
	{
		calculateButton.setText(string);
	}
	
	public void refreshFrame()
	{
		this.add(myPanel);
		// refresh JFrame
		revalidate();
		repaint();
	}

	public void setTextFieldValues()
	{
		firstField.setText("Ammortization, in Months");
		secondField.setText("Total Amount Loaned");
		thirdField.setText("Annual Interest Rate (%)");
		fourthField.setText("Compound Frequency");
	}
	
	public void setUpButtonGroup()
	{
		myPanel.add(checkTitle);
		buttonGroup.add(checkOne);
		buttonGroup.add(checkTwo);
		buttonGroup.add(checkThree);
		myPanel.add(checkOne);
		myPanel.add(checkTwo);
		myPanel.add(checkThree);
		//this.add(buttonGroup);
		refreshFrame();
		
	}
	
	/**
	 * 
	 * add action listener to button
	 * 
	 * @param listenerForCalcButton
	 */
	void addCalculationListener(ActionListener listenerForCalcButton)
	{
		calculateButton.addActionListener(listenerForCalcButton);
	}
	
	/**
	 * 
	 * error message message
	 * 
	 * @param errorMessage
	 */
	
	void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}

}
