package mortgage;

public class MortgageController {
	private MortgageView theView;
	private MortgageModel theModel;
	
	public MortgageController(MortgageView theView, MortgageModel theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addCalculationListener(new CalculateListener(this));
		this.theView.addTextBoxListener(new TextBoxListener());
	}
	
	public void calculateTotal()
	{
		double firstNumber, secondNumber, thirdNumber, fourthNumber;
		double monthlyPayment, totalInterest, interestPrinciple;
		double interestPrincipleRatio, interestYear, interestMonth, ammortization;
		double checkValue;
		
		try {
			// get user input
			firstNumber = theView.getFirstNumber();
			secondNumber = theView.getSecondNumber();
			thirdNumber = theView.getThirdNumber();
			fourthNumber = theView.getFourthNumber();
			
			// get check value, check it has been selected
			checkValue = theView.getCheckValue();
			if (checkValue < 0) {
				theView.displayErrorMessage("Please check an option");
				return;
			}
			
			// calculate values
			theModel.calculateMortgageValues(firstNumber, secondNumber, thirdNumber, fourthNumber, checkValue);
			
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
			theView.displayErrorMessage("You need to enter four values");
			theView.resetCalculator();
		}
		
	}
	
	public void resetCalculator()
	{
		theView.resetCalculator();
	}

}