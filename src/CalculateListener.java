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
			double firstNumber, secondNumber, thirdNumber, fourthNumber = 0;
			double monthlyPayment, totalInterest, interestPrinciple = 0;
			double interestPrincipleRatio, interestYear, interestMonth, ammortization = 0;
			
			try {
				// get user input
				firstNumber = theView.getFirstNumber();
				secondNumber = theView.getSecondNumber();
				thirdNumber = theView.getThirdNumber();
				fourthNumber = theView.getFourthNumber();
				
				// calculate values
				theModel.calculateMortgageValues(firstNumber, secondNumber, thirdNumber, fourthNumber);
				
				// get values
				monthlyPayment = theModel.getMonthlyPayment();
				totalInterest = theModel.getInterestPaid();
				interestPrinciple = theModel.getInterestPrinciple();
				interestPrincipleRatio = theModel.getInterestPrincipleRatio();
				interestYear = theModel.getInterestYear();
				interestMonth = theModel.getInterestMonth();
				ammortization = theModel.getAmmortization();

				// set values
				theView.setResultOne(monthlyPayment);
				theView.setResultTwo(totalInterest);
				theView.setResultThree(interestPrinciple);
				theView.setResultFour(interestPrincipleRatio);
				theView.setResultFive(interestYear);
				theView.setResultSix(interestMonth);
				theView.setResultSeven(ammortization);
				
				// update JFrame
				theView.hideCalculator();
				theView.showResults();	
			}
			catch(NumberFormatException ex)
			{
				theView.displayErrorMessage("You need to enter three integers");
			}
			
		}
		
		public void resetCalculator()
		{
			theView.hideResults();
			theView.showCalculator();
		}
		
		
}

