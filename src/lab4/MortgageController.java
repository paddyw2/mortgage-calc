package lab4;

public class MortgageController {
	private MortgageCalculatorView calcView;
	private MortgageScheduleView scheduleView;
	private MortgageModel theModel;
	
	public MortgageController(MortgageCalculatorView calcView, MortgageModel theModel, MortgageScheduleView scheduleView)
	{
		this.calcView = calcView;
		this.scheduleView = scheduleView;
		this.theModel = theModel;
		
		this.calcView.addCalculationListener(new CalculateListener(this));
		this.calcView.addTextBoxListener(new TextBoxListener());
		
		ScheduleListener scheduleListener = new ScheduleListener(this);
		this.calcView.addScheduleListener(scheduleListener);
		this.scheduleView.addScheduleListener(scheduleListener);
		
		
	}
	
	public void showMortgageSchedule()
	{
		Object[][] data = theModel.returnScheduleInfo();

		scheduleView.updateTable(data);
		scheduleView.setVisible(true);
	}
	
	public void hideMortgageSchedule()
	{
		scheduleView.setVisible(false);
	}
	
	public void calculateTotal()
	{
		double firstNumber, secondNumber, thirdNumber, fourthNumber;
		double monthlyPayment, totalInterest, interestPrinciple;
		double interestPrincipleRatio, interestYear, interestMonth, ammortization;
		double checkValue;
		
		try {
			// get user input
			firstNumber = calcView.getFirstNumber();
			secondNumber = calcView.getSecondNumber();
			thirdNumber = calcView.getThirdNumber();
			fourthNumber = calcView.getFourthNumber();
			
			// get check value, check it has been selected
			checkValue = calcView.getCheckValue();
			if (checkValue < 0) {
				calcView.displayErrorMessage("Please check an option");
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
			calcView.setResultOne(monthlyPayment);
			calcView.setResultTwo(totalInterest);
			calcView.setResultThree(interestPrinciple);
			calcView.setResultFour(interestPrincipleRatio);
			calcView.setResultFive(interestYear);
			calcView.setResultSix(interestMonth);
			calcView.setResultSeven(ammortization);
			
			// update JFrame
			calcView.hideCalculator();
			calcView.showResults();	
		}
		catch(NumberFormatException ex)
		{
			calcView.displayErrorMessage("You need to enter four values");
			calcView.resetCalculator();
		}
		
	}
	
	public void resetCalculator()
	{
		calcView.resetCalculator();
	}

}