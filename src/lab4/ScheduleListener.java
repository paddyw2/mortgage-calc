package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
* <h1>Listener: Show Mortgage Schedule Window</h1>
* This listener shows the mortgage schedule frame when the view schedule
* button is clicked 
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class ScheduleListener implements ActionListener
	{
		private MortgageController theController;
		
		// constructor takes the controller as parameter and sets
		// it as a variable to allow the listener to pass commands
		// to the controller
		public ScheduleListener(MortgageController controller)
		{
			theController = controller;
		}

		// check the button clicked is the view schedule button,
		// and if it is, show the mortgage schedule window
		// the showMortgageSchedule() method also effectively
		// refreshes the data if a new calculation has occurred
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton source = (JButton) e.getSource();
			
			if (source.getText().equals("View Schedule"))
				theController.showMortgageSchedule();
			else if (source.getText().equals("Close"))
				theController.hideMortgageSchedule();
		}
}