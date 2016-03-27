package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
* <h1>Listener: Calculate Mortgage Button</h1>
* This listener toggles the button text and calculator view to show
* the users results. It also triggers the methods to send the data
* to the model for calculation.
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class CalculateListener implements ActionListener
	{
		private MortgageController theController;
		
		// constructor takes the controller as parameter and sets
		// it as a variable to allow the listener to pass commands
		// to the controller
		public CalculateListener(MortgageController controller)
		{
			theController = controller;
		}
		
		// if the button text is calculate, then calculate
		// and display user mortgage details
		// otherwise, reset calculator
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton source = (JButton) e.getSource();
			
			if (source.getText().equals("Calculate"))
				theController.calculateMortgage();
			else
				theController.resetCalculator();
		}	
}

