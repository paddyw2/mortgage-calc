package lab4;

/**
* <h1>Application Driver Class</h1>
* The MVCMortgage class creates the model, two views, and controller, and
* launches the application.
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class MVCMortgage {

	public static void main(String[] args)
	{
		MortgageCalculatorView calcView = new MortgageCalculatorView();
		MortgageScheduleView scheduleView = new MortgageScheduleView();
		MortgageModel theModel = new MortgageModel();
		
		MortgageController theController = new MortgageController(calcView, theModel, scheduleView);
		
		calcView.setVisible(true);
	}
}