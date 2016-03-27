package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
* <h1>Listener: Toggle Text and Update Table</h1>
* This listener toggles the text on the payment schedule frame button
* that allows the user to view their schedule either by month or by
* year. It also updates the table to show the appropriate information.
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class ScheduleViewListener implements ActionListener {

	private MortgageController theController;
	
	// constructor takes the controller as parameter and sets
	// it as a variable to allow the listener to pass commands
	// to the controller
	public ScheduleViewListener(MortgageController controller)
	{
		theController = controller;
	}
	
	// when button is clicked, if it reads "View by Month" then
	// show yearly schedule and change the text
	// if not, show monthly schedule and change the text
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton source = (JButton) e.getSource();
		
		if (source.getText().equals("View by Month"))
			theController.changeScheduleMonth();
		else
			theController.changeScheduleYear();
	}
}