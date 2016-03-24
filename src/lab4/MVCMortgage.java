package lab4;

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