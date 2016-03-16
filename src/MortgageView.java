import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MortgageView extends JFrame {

	private final int FIELD_WIDTH = 16;
	
	
	// calculator fields
	private JTextField firstField = new JTextField(FIELD_WIDTH);
	private JTextField secondField = new JTextField(FIELD_WIDTH);
	private JTextField thirdField = new JTextField(FIELD_WIDTH);
	private JButton calculateButton = new JButton("Calculate");
	// calcSolution added later
	private JTextField calcSolution = new JTextField(FIELD_WIDTH);
	
	private JPanel myPanel;
	
	public MortgageView()
	{	
		super("Mortgage Calculator");
		
		myPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 200);
		this.setLocation(200,200);
		
		Container container = getContentPane();
		container.setLayout(new GridLayout());
		
		setTextFieldValues();
		
		myPanel.add(firstField);
		myPanel.add(secondField);
		myPanel.add(thirdField);
		myPanel.add(calculateButton);
		
		this.add(myPanel);
	}
	
	public int getFirstNumber()
	{
		return Integer.parseInt(firstField.getText());
	}
	
	public int getSecondNumber()
	{
		return Integer.parseInt(secondField.getText());
	}
	
	public int getThirdNumber()
	{
		return Integer.parseInt(thirdField.getText());
	}
	
	public int getCalcSolution()
	{
		return Integer.parseInt(calcSolution.getText());
	}
	
	public void setCalcSolution(int solution)
	{
		calcSolution.setText(Integer.toString(solution));
	}
	
	public void addCalcSolution()
	{
		// change button to allow reset
		calculateButton.setText("Reset");
		// add calcsolution field to panel
		myPanel.add(calcSolution);
		this.add(myPanel);
		// refresh JFrame
		revalidate();
		repaint();
	}
	
	public void resetCalculator()
	{
		removeCalcSolution();
		resetCalcButton();
		setTextFieldValues();
	}
	
	public void removeCalcSolution()
	{
		myPanel.remove(calcSolution);
		this.add(myPanel);
		// refresh JFrame
		revalidate();
		repaint();
	}
	
	public void resetCalcButton()
	{
		calculateButton.setText("Calculate");
	}
	
	public void setTextFieldValues()
	{
		firstField.setText("Number of Monthly Payments");
		secondField.setText("Total Amount Loaned");
		thirdField.setText("Annual Interest Rate");
	}
	
	void addCalculationListener(ActionListener listenerForCalcButton)
	{
		calculateButton.addActionListener(listenerForCalcButton);
	}
	
	void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}

}
