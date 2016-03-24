package lab4;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class WindowListener implements WindowFocusListener {
	
	private MortgageController controller;
	
	public WindowListener(MortgageController controller) {
		
		this.controller = controller;
		
	}

	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		controller.setButtonFocus();
		
	}

	@Override
	public void windowLostFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
