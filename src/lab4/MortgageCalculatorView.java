package lab4;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.WindowFocusListener;
import javax.swing.*;

/**
* <h1>Calculator View</h1>
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
	private final int FRAME_WIDTH = 250;
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

	// Constructor
	// class constructor, sets up the initial calculator view
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
	// return the double value of each calculator text field
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
	
	// show calculator components by adding them to the panel
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
	
	// hide calculator components by removing them from the panel
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
	
	// show result components by adding them to the panel
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
	
	// hide result components by removing them from the panel
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
	
	// clear check box selection
	public void resetCheckBoxes()
	{
		buttonGroup.clearSelection();
	}
	
	// Result Label Setters
	// set the string values of the result label components
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
	
	// GridBagLayout method for adding components to the panel, used instead of the simpler
	// add() method
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
	
	// set the String value of the calculate button
	public void setCalcButton(String string)
	{
		calculateButton.setText(string);
	}
	
	// refresh the JFrame to update changes
	public void refreshFrame()
	{
		this.add(myPanel);
		// refresh JFrame
		revalidate();
		repaint();
	}

	// Constructor Helper Methods
	// set up text field values for main calculator
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
	
	// set up check box button group
	public void setUpButtonGroup()
	{
		addItem(myPanel, checkTitle, 2, 12, 1, 1, GridBagConstraints.NORTH);
		buttonGroup.add(checkOne);
		buttonGroup.add(checkTwo);
		buttonGroup.add(checkThree);
		addItem(myPanel, checkOne, 2, 14, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, checkTwo, 2, 16, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, checkThree, 2, 18, 1, 1, GridBagConstraints.NORTH);
		//this.add(buttonGroup);
		refreshFrame();
		
	}
	
	// return the boolean state of each check box
	public boolean getCheckOne()
	{
		if (checkOne.isSelected())
			return true;
		else
			return false;
	}
	
	public boolean getCheckTwo()
	{
		if (checkTwo.isSelected())
			return true;
		else
			return false;
	}
	
	public boolean getCheckThree()
	{
		if (checkThree.isSelected())
			return true;
		else
			return false;
	}
	
	// return the desired double value, depending on which
	// check box is selected
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
	
	// reset calculator to default state
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
	// these methods allow the necessary listeners to be added
	// the appropriate components
	public void addCalculationListener(ActionListener listenerForCalcButton)
	{
		calculateButton.addActionListener(listenerForCalcButton);
	}
	
	public void addScheduleListener(ActionListener scheduleListener)
	{
		scheduleButton.addActionListener(scheduleListener);
	}
	
	public void addFocusListener(FocusListener listenForFocus)
	{
		firstField.addFocusListener(listenForFocus);
		secondField.addFocusListener(listenForFocus);
		thirdField.addFocusListener(listenForFocus);
		fourthField.addFocusListener(listenForFocus);
	}
	
	public void addWinFocusListener(WindowFocusListener windowListener)
	{
		this.addWindowFocusListener(windowListener);
	}
	
	// display a JOptionPane error message
	void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}

}