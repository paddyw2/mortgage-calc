import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CalculateListener implements ActionListener
	{
		private MortgageView theView;
		private MortgageModel theModel;
		
		public CalculateListener(MortgageView view, MortgageModel model)
		{
			theView = view;
			theModel = model;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton source = (JButton) e.getSource();
			
			if (source.getText().equals("Calculate"))
				calculateTotal();
			else
				resetCalculator();
			
		}
		
		public void calculateTotal()
		{
			int firstNumber, secondNumber, thirdNumber = 0;
			
			try {
				firstNumber = theView.getFirstNumber();
				secondNumber = theView.getSecondNumber();
				thirdNumber = theView.getThirdNumber();
				
				theModel.addTwoNumbers(firstNumber, secondNumber, thirdNumber);
				theView.addCalcSolution();
				theView.setCalcSolution(theModel.getCalculationValue());
				
			}
			
			catch(NumberFormatException ex)
			{
				theView.displayErrorMessage("You need to enter three integers");
			}
			
		}
		
		public void resetCalculator()
		{
			theView.resetCalculator();
		}
		
		
}

