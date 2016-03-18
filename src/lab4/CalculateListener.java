package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CalculateListener implements ActionListener
	{
		private MortgageController theController;
		
		public CalculateListener(MortgageController controller)
		{
			theController = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton source = (JButton) e.getSource();
			
			if (source.getText().equals("Calculate"))
				theController.calculateTotal();
			else
				theController.resetCalculator();
			
		}
		
		
}

