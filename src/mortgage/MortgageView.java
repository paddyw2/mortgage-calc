package mortgage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;

public class MortgageView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// field width constant
	private final int FIELD_WIDTH = 15;
	private final int FRAME_WIDTH = 250;
	private final int FRAME_HEIGHT = 350;
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
	private JCheckBox checkOne = new JCheckBox("Weekly");
	private JCheckBox checkTwo = new JCheckBox("Bi-Weekly");
	private JCheckBox checkThree = new JCheckBox("Monthly");
	
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
		//Container container = getContentPane();
		myPanel.setLayout(new GridBagLayout());
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
		addItem(myPanel, firstField, 2, 4, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, secondField, 2, 6, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, thirdField, 2, 8, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, fourthField, 2, 10, 1, 1, GridBagConstraints.NORTH);
		setUpButtonGroup();
		addItem(myPanel, calculateButton, 2, 20, 1, 1, GridBagConstraints.NORTH);
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
		addItem(myPanel, resultOne, 2, 4, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultTwo, 2, 6, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultThree, 2, 8, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultFour, 2, 10, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultFive, 2, 12, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultSix, 2, 14, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, resultSeven, 2, 16, 1, 1, GridBagConstraints.NORTH);
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
	
	public void resetCheckBoxes()
	{
		buttonGroup.clearSelection();
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
	 * layout methods
	 * 
	 */
	private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
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
	
	public void resetCalculator()
	{
		hideResults();
		showCalculator();
		resetCheckBoxes();
	}
	
	/**
	 * 
	 * add action listener to button
	 * 
	 * @param listenerForCalcButton
	 */
	public void addCalculationListener(ActionListener listenerForCalcButton)
	{
		calculateButton.addActionListener(listenerForCalcButton);
	}
	
	public void addTextBoxListener(MouseListener listenForClick)
	{
		firstField.addMouseListener(listenForClick);
		secondField.addMouseListener(listenForClick);
		thirdField.addMouseListener(listenForClick);
		fourthField.addMouseListener(listenForClick);
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
