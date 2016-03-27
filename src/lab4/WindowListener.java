package lab4;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
* <h1>Listener: Set Focus on Calculate Button by Default</h1>
* This listener sets the focus on the calculate button when the calculator
* window comes into focus to keep all the helper text on each field visible
* to the user until their select a field
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class WindowListener implements WindowFocusListener {
	
	private MortgageController controller;
	
	/**
	 * <h1>Constructor</h1>
	 * constructor takes the controller as parameter and sets
	 * it as a variable to allow the listener to pass commands
	 * to the controller
	 * 
	 * @param controller
	 */
	public WindowListener(MortgageController controller)
	{
		this.controller = controller;
	}

	/**
	 * <h1>Set Button Focus</h1>
	 * 
	 * when the calculator view is in focus, set the default
	 * focus to be on the calculate button
	 * 
	 */
	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		controller.setButtonFocus();
		
	}

	@Override
	public void windowLostFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}