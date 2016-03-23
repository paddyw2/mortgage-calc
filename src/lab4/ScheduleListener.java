package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ScheduleListener implements ActionListener
	{
		private MortgageController theController;
		
		public ScheduleListener(MortgageController controller)
		{
			theController = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton source = (JButton) e.getSource();
			
			if (source.getText().equals("View Schedule"))
				theController.showMortgageSchedule();
			else
				theController.hideMortgageSchedule();
			
		}
		
		
}