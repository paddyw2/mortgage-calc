package lab4;

/**
* <h1>Mortgage Controller</h1>
* This class acts as the controller between the model and the two views.
* It determines what the view shows and what data is transferred from the
* model to the two views.
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class MortgageController {
	
	// variables for the two views and the model
	private MortgageCalculatorView calcView;
	private MortgageScheduleView scheduleView;
	private MortgageModel theModel;
	
	// Constructor
	/**
	 * <h1>Constructor</h1>
	 * 
	 * takes the two views and the model as parameters and sets them as variables
	 * it also then creates the necessary listeners and adds them to the appropriate
	 * components in each of the two views
	 * 
	 * @param calcView
	 * @param theModel
	 * @param scheduleView
	 */
	public MortgageController(MortgageCalculatorView calcView, MortgageModel theModel, MortgageScheduleView scheduleView)
	{
		this.calcView = calcView;
		this.scheduleView = scheduleView;
		this.theModel = theModel;
		
		// create and add listeners to view components
		this.calcView.addCalculationListener(new CalculateListener(this));
		this.calcView.addFocusListener(new TextFocusListener());
		this.calcView.addWinFocusListener(new WindowListener(this));
		this.scheduleView.addScheduleViewListener(new ScheduleViewListener(this));
		
		ScheduleListener scheduleListener = new ScheduleListener(this);
		this.calcView.addScheduleListener(scheduleListener);
		this.scheduleView.addScheduleListener(scheduleListener);
	}
	
	// gets the latest mortgage schedule data from the model, and passes
	// it to the mortgage schedule view to be updated
	/**
	 * <h1>Show Initial Mortgage Schedule</h1>
	 * 
	 * This method shows the first mortgage schedule view. The default is a yearly
	 * view but if there is not enough information, then that is changed to a
	 * monthly view
	 * 
	 */
	public void showMortgageSchedule()
	{
		Object[][] data = theModel.returnScheduleInfo(true);
		
		// if amortization less than 12 months, show monthly by default
		if (data[0][0].equals("Error")) {
			changeSchedule(false);
		} else {
			scheduleView.updateTable(data);
			scheduleView.setVisible(true);
		}
	}
	
	/**
	 * <h1>Hide Mortgage Schedule View</h1>
	 * 
	 * Set mortgage schedule view visibility to false
	 * 
	 */
	public void hideMortgageSchedule()
	{
		scheduleView.setVisible(false);
	}
	
	/**
	 * 
	 * <h1>Change Schedule View to Yearly</h1>
	 * 
	 * Get the latest yearly schedule data from the model and update
	 * the table in schedule view to display this information. Also toggles
	 * the buttons and table header to reflect the change.
	 * 
	 * @param year
	 * 
	 */
	public void changeSchedule(boolean year)
	{
		Object[][] data = theModel.returnScheduleInfo(year);
		// if amortization is less than 12 months, show error and return
		if (data[0][0].equals("Error")) {
			scheduleView.displayErrorMessage("Amortization period not long enough for yearly schedule");
			return;
		}
		scheduleView.updateDuration();
		scheduleView.updateButton();
		scheduleView.updateTable(data);
		scheduleView.setVisible(true);
	}
	
	/**
	 * <h1>Set Window Focus on Calculate Button</h1>
	 * 
	 * When the calculator view is in focus, the default
	 * focus is set on the calculate button in order to
	 * allow all the text field helper text to be viewable 
	 * 
	 */
	public void setButtonFocus()
	{
		calcView.setButtonFocus();
	}
	
	/**
	 * <h1>Calculate Mortgage Payment</h1>
	 * 
	 * This method is triggered when the calculate button is clicked
	 * and takes the values from the text fields and sends the data
	 * to the model to be calculated
	 * It then sends the received data back to the view to update the
	 * result labels
	 */
	public void calculateMortgage()
	{
		// declare variables to be used
		double firstNumber, secondNumber, thirdNumber, fourthNumber;
		double monthlyPayment, totalInterest, interestPrinciple;
		double interestPrincipleRatio, interestYear, interestMonth, amortization;
		double checkValue;
		String blendedSchedule;
		
		// an error will occur if the user does not enter a double value
		// in which case an error message will be shown
		try {
			// get user text field input
			firstNumber = calcView.getFirstNumber();
			secondNumber = calcView.getSecondNumber();
			thirdNumber = calcView.getThirdNumber();
			fourthNumber = calcView.getFourthNumber();
			
			// get check value, ensure it has been selected
			checkValue = calcView.getCheckValue();
			if (checkValue < 0) {
				calcView.displayErrorMessage("Please check an option");
				return;
			} else if(checkValue == 52) {
				blendedSchedule = "Weekly";
			} else if(checkValue == 26) {
				blendedSchedule = "Bi-Weekly";
			} else {
				blendedSchedule = "Monthly";
			}
			
			// calculate values
			theModel.calculateMortgageValues(firstNumber, secondNumber, thirdNumber, fourthNumber, checkValue);
			
			// get updated values from the model
			monthlyPayment = theModel.getMonthlyPayment();
			totalInterest = theModel.getInterestPaid();
			interestPrinciple = theModel.getInterestPrinciple();
			interestPrincipleRatio = theModel.getInterestPrincipleRatio();
			interestYear = theModel.getInterestYear();
			interestMonth = theModel.getInterestMonth();
			amortization = theModel.getAmmortization();

			// set the new values in the view
			calcView.setResultOne(monthlyPayment, blendedSchedule);
			calcView.setResultTwo(totalInterest);
			calcView.setResultThree(interestPrinciple);
			calcView.setResultFour(interestPrincipleRatio);
			calcView.setResultFive(interestYear);
			calcView.setResultSix(interestMonth);
			calcView.setResultSeven(amortization);
			
			// update JFrame to display results
			calcView.hideCalculator();
			calcView.showResults();	
		}
		catch(NumberFormatException ex)
		{
			// show error message if text field input not value double
			calcView.displayErrorMessage("You need to enter four values");
			calcView.resetCalculator();
		}
	}

	/**
	 * <h1>Reset Calculator</h1>
	 * 
	 * changes the view back to the default calculator
	 * 
	 */
	public void resetCalculator()
	{
		calcView.resetCalculator();
	}
}