package lab4;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.WindowFocusListener;
import javax.swing.*;

/**
* <h1>Mortgage Calculator View</h1>
* This class creates the view for the mortgage calculator window
* that the user enters their information into
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class MortgageCalculatorView extends JFrame {

	// field width and frame constants
	private final int FIELD_WIDTH = 15;
	private final int FRAME_WIDTH = 280;
	private final int FRAME_HEIGHT = 350;
	private final int LOCATION = 150;
	
	// calculator fields
	private JTextField firstField = new JTextField(FIELD_WIDTH);
	private JTextField secondField = new JTextField(FIELD_WIDTH);
	private JTextField thirdField = new JTextField(FIELD_WIDTH);
	private JTextField fourthField = new JTextField(FIELD_WIDTH);
	private JButton calculateButton = new JButton();
	
	// result labels
	private JLabel padding = new JLabel();
	private JLabel resultOne = new JLabel();
	private JLabel resultTwo = new JLabel();
	private JLabel resultThree = new JLabel();
	private JLabel resultFour = new JLabel();
	private JLabel resultFive = new JLabel();
	private JLabel resultSix = new JLabel();
	private JLabel resultSeven = new JLabel();
	private JButton scheduleButton = new JButton("View Schedule");
	
	// check boxes
	private JLabel checkTitle = new JLabel("Choose Payment Schedule");
	private JCheckBox checkOne = new JCheckBox("Weekly");
	private JCheckBox checkTwo = new JCheckBox("Bi-Weekly");
	private JCheckBox checkThree = new JCheckBox("Monthly");
	
	// main panel and button group
	private JPanel myPanel;
	private ButtonGroup buttonGroup;

	/**
	 * <h1>Constructor</h1>
	 * class constructor, sets up the initial calculator view
	 * 
	 */
	public MortgageCalculatorView()
	{	
		super("Mortgage Calculator");
		myPanel = new JPanel();
		buttonGroup = new ButtonGroup();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocation(LOCATION,LOCATION);
		myPanel.setLayout(new GridBagLayout());
		
		// show calculator as initial view
		showCalculator();
	}
	
	// Text Field Get Methods
	/**
	 * <h1>Get firstField double value</h1>
	 * 
	 * return the double value of calculator text field
	 * 
	 * @return double
	 */
	public double getFirstNumber()
	{
		return Double.parseDouble(firstField.getText());
	}
	
	/**
	 * <h1>Get secondField double value</h1>
	 * 
	 * return the double value of calculator text field
	 * 
	 * @return double
	 */
	public double getSecondNumber()
	{
		return Double.parseDouble(secondField.getText());
	}
	
	/**
	 * <h1>Get thirdField double value</h1>
	 * 
	 * return the double value of calculator text field
	 * 
	 * @return double
	 */
	public double getThirdNumber()
	{
		return Double.parseDouble(thirdField.getText());
	}
	
	/**
	 * <h1>Get fourthField double value</h1>
	 * 
	 * return the double value of calculator text field
	 * 
	 * @return double
	 */
	public double getFourthNumber()
	{
		return Double.parseDouble(fourthField.getText());
	}

	/**
	 * <h1>Show Calculator</h1>
	 * show calculator components by adding them to the panel
	 * 
	 */
	public void showCalculator()
	{
		setTextFieldValues();
		setCalcButton("Calculate");
		addItem(myPanel, padding, 2, 0, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, firstField, 2, 4, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, secondField, 2, 6, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, thirdField, 2, 8, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, fourthField, 2, 10, 1, 1, GridBagConstraints.NORTH);
		setUpButtonGroup();
		addItem(myPanel, calculateButton, 2, 20, 1, 1, GridBagConstraints.NORTH);
		refreshFrame();
	}
	
	/**
	 * <h1>Hide Calculator</h1>
	 * hide calculator components by removing them from the panel
	 * 
	 */
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
	 * <h1>Show Result Labels</h1>
	 * show result components by adding them to the panel
	 * 
	 */
	public void showResults()
	{
		setCalcButton("Reset");
		addItem(myPanel, resultOne, 2, 4, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultTwo, 2, 6, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultThree, 2, 8, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultFour, 2, 10, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultFive, 2, 12, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultSix, 2, 14, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultSeven, 2, 16, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, scheduleButton, 2, 18, 1, 1, GridBagConstraints.NORTH);
		refreshFrame();
	}
	
	/**
	 * <h1>Hide Result Labels</h1>
	 * hide result components by removing them from the panel
	 * 
	 */
	public void hideResults()
	{
		myPanel.remove(resultOne);
		myPanel.remove(resultTwo);
		myPanel.remove(resultThree);
		myPanel.remove(resultFour);
		myPanel.remove(resultFive);
		myPanel.remove(resultSix);
		myPanel.remove(resultSeven);
		myPanel.remove(scheduleButton);
		refreshFrame();
	}

	/**
	 * <h1>Clear Text Box Selection</h1>
	 * clear all check boxes
	 * 
	 */
	public void resetCheckBoxes()
	{
		buttonGroup.clearSelection();
	}
	
	// Result Label Setters
	/**
	 * <h1>Set resultOne Label Text</h1>
	 * set the string values of the result label component
	 * 
	 * @param result
	 */
	public void setResultOne(double result, String schedule)
	{
		resultOne.setText("Blended " + schedule + " Payment: $" + Double.toString(result));
	}
	
	/**
	 * <h1>Set resultTwo Label Text</h1>
	 * set the string values of the result label component
	 * 
	 * @param result
	 */
	public void setResultTwo(double result)
	{
		resultTwo.setText("Total Interest Paid: $" + Double.toString(result));
	}
	
	/**
	 * <h1>Set resultThree Label Text</h1>
	 * set the string values of the result label component
	 * 
	 * @param result
	 */
	public void setResultThree(double result)
	{
		resultThree.setText("Total Interest and Principle: $" + Double.toString(result));
	}
	
	/**
	 * <h1>Set resultFour Label Text</h1>
	 * set the string values of the result label component
	 * 
	 * @param result
	 */
	public void setResultFour(double result)
	{
		resultFour.setText("Interest/Principle Ratio: " + Double.toString(result));
	}
	
	/**
	 * <h1>Set resultFive Label Text</h1>
	 * set the string values of the result label component
	 * 
	 * @param result
	 */
	public void setResultFive(double result)
	{
		resultFive.setText("Average Interest per Year: $" + Double.toString(result));
	}
	
	/**
	 * <h1>Set resultSix Label Text</h1>
	 * set the string values of the result label component
	 * 
	 * @param result
	 */
	public void setResultSix(double result)
	{
		resultSix.setText("Average Interest per Month: $" + Double.toString(result));
	}
	
	/**
	 * <h1>Set resultSeven Label Text</h1>
	 * set the string values of the result label component
	 * 
	 * @param result
	 */
	public void setResultSeven(double result)
	{
		resultSeven.setText("Ammortization, in Years: " + Double.toString(result));
	}
	
	/**
	 * <h1>GridBagLayout Add Method</h1>
	 * 
	 * GridBagLayout add helper method to ensure correct placement of new components
	 * onto the panel
	 * 
	 * @param p
	 * @param c
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param align
	 */
	public void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
	    GridBagConstraints gc = new GridBagConstraints();
	    gc.gridx = x;
	    gc.gridy = y;
	    gc.gridwidth = width;
	    gc.gridheight = height;
	    gc.weightx = 100.0;
	    gc.weighty = 100.0;
	    gc.insets = new Insets(5, 5, 5, 5);
	    gc.anchor = align;
	    gc.fill = GridBagConstraints.NONE;
	    p.add(c, gc);
	  }
	
	/**
	 * <h1>Set the Calculate Button Text</h1>
	 * 
	 * set the String value of the calculate button
	 * 
	 * @param string
	 */
	public void setCalcButton(String string)
	{
		calculateButton.setText(string);
	}
	
	/**
	 * <h1>Refresh and Update JFrame</h1>
	 * 
	 * method to update JFrame with any
	 * panel adds or removes that may have
	 * taken place
	 * 
	 */
	public void refreshFrame()
	{
		this.add(myPanel);
		// refresh JFrame
		revalidate();
		repaint();
	}

	// Constructor Helper Methods
	/**
	 * <h1>Set Calculator Text Field Values</h1>
	 * 
	 * set up text field values for main calculator
	 * 
	 */
	public void setTextFieldValues()
	{
		firstField.setName("firstField");
		firstField.setText("Ammortization, in Months");
		firstField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		secondField.setName("secondField");
		secondField.setText("Total Amount Loaned");
		secondField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		thirdField.setName("thirdField");
		thirdField.setText("Annual Interest Rate (%)");
		thirdField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		fourthField.setName("fourthField");
		fourthField.setText("Compound Frequency");
		fourthField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	}

	/**
	 * <h1>Set Up Check Box Button Group</h1>
	 * 
	 * add check boxes to the button group
	 * 
	 */
	public void setUpButtonGroup()
	{
		addItem(myPanel, checkTitle, 2, 12, 1, 1, GridBagConstraints.NORTH);
		buttonGroup.add(checkOne);
		buttonGroup.add(checkTwo);
		buttonGroup.add(checkThree);
		addItem(myPanel, checkOne, 2, 14, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, checkTwo, 2, 16, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, checkThree, 2, 18, 1, 1, GridBagConstraints.NORTH);
		refreshFrame();
	}
	
	/**
	 * <h1>Get checkOne Boolean Value</h1>
	 * 
	 * returns the boolean value of the check box
	 * 
	 * @return boolean
	 */
	public boolean getCheckOne()
	{
		if (checkOne.isSelected())
			return true;
		else
			return false;
	}
	
	/**
	 * <h1>Get checkTwo Boolean Value</h1>
	 * 
	 * returns the boolean value of the check box
	 * 
	 * @return boolean
	 */
	public boolean getCheckTwo()
	{
		if (checkTwo.isSelected())
			return true;
		else
			return false;
	}
	
	/**
	 * <h1>Get checkThree Boolean Value</h1>
	 * 
	 * returns the boolean value of the check box
	 * 
	 * @return boolean
	 */
	public boolean getCheckThree()
	{
		if (checkThree.isSelected())
			return true;
		else
			return false;
	}
	
	/**
	 * <h1>Return the Double Value of the Check Box Group</h1>
	 * 
	 * each check box represents a different double payment
	 * frequency, so return the corresponding value
	 * 
	 * @return double
	 */
	public double getCheckValue()
	{
		if (getCheckOne())
			return 52.0;
		else if (getCheckTwo())
			return 26.0;
		else if (getCheckThree())
			return 12.0;
		else
			return -1.0;
	}
	
	/**
	 * <h1>Reset Calculator</h1>
	 * 
	 * reset calculator to default state
	 * 
	 */
	public void resetCalculator()
	{
		hideResults();
		showCalculator();
		resetCheckBoxes();
	}
	
	// set component focus on the calculate button, allowing
	// the text field helper text to be seen
	public void setButtonFocus()
	{
		calculateButton.requestFocusInWindow();
	}
	
	// Listener Add Methods
	/**
	 * <h1>Add Calculation Listener</h1>
	 * 
	 * adds listener to calculateButton button
	 * 
	 * @param listenerForCalcButton
	 */
	public void addCalculationListener(ActionListener listenerForCalcButton)
	{
		calculateButton.addActionListener(listenerForCalcButton);
	}
	
	/**
	 * <h1>Add Schedule Listener</h1>
	 * 
	 * adds listener to scheduleButton button
	 * 
	 * @param scheduleListener
	 */
	public void addScheduleListener(ActionListener scheduleListener)
	{
		scheduleButton.addActionListener(scheduleListener);
	}
	
	/**
	 * <h1>Add Focus Listener</h1>
	 * 
	 * adds focus listener to all text fields
	 * 
	 * @param listenForFocus
	 */
	public void addFocusListener(FocusListener listenForFocus)
	{
		firstField.addFocusListener(listenForFocus);
		secondField.addFocusListener(listenForFocus);
		thirdField.addFocusListener(listenForFocus);
		fourthField.addFocusListener(listenForFocus);
	}
	
	/**
	 * <h1>Add Window Focus Listener</h1>
	 * 
	 * adds window focus listener to window
	 * 
	 * @param windowListener
	 */
	public void addWinFocusListener(WindowFocusListener windowListener)
	{
		this.addWindowFocusListener(windowListener);
	}
	
	/**
	 * <h1>Display Error Message</h1>
	 * 
	 * display a JOptionPane error message
	 * 
	 * @param errorMessage
	 */
	public void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}

}