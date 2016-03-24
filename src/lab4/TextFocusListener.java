package lab4;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class TextFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent arg0) {
		JTextField source = (JTextField) arg0.getSource();
		try {
			Double.parseDouble(source.getText());
		} catch(NumberFormatException ex) {
			source.setText("");
		}
		
	}

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
