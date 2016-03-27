package lab4;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
* <h1>Listener: Clear Text Fields on Focus</h1>
* This listener clears text field helper text on focus, and replaces it
* if the user has not entered a value double value
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class TextFocusListener implements FocusListener {

	/**
	 * <h1>Clear Text Field on Focus</h1>
	 * 
	 * If a text field is selected or focused on, clear
	 * the default helper text
	 * 
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		JTextField source = (JTextField) arg0.getSource();
		try {
			Double.parseDouble(source.getText());
		} catch(NumberFormatException ex) {
			source.setText("");
		}
	}

	/**
	 * <h1>On Focus Lost, Check Field Value Validity</h1>
	 * 
	 * on focus lost, check if the user entered a non-double value
	 * and if so, set the field text to the default helper text
	 * 
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		JTextField source = (JTextField) arg0.getSource();
		try {
			Double.parseDouble(source.getText());
		} catch(NumberFormatException ex) {
			
			String name = source.getName();
			
			switch (name) {
			
				case "firstField":
				
					source.setText("Ammortization, in Months");
					break;
					
				case "secondField":
					source.setText("Total Amount Loaned");
					break;
					
				case "thirdField":
					source.setText("Annual Interest Rate (%)");
					break;
					
				case "fourthField":
					source.setText("Compound Frequency");
					break;
					
				default:
					System.out.println("Invalid input field");
					break;
					
			}
			
		}
	}

}