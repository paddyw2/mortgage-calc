package lab4;

public class MVCMortgage {

	public static void main(String[] args)
	{
		MortgageView theView = new MortgageView();
		MortgageViewTwo viewTwo = new MortgageViewTwo();
		MortgageModel theModel = new MortgageModel();
		
		MortgageController theController = new MortgageController(theView, theModel, viewTwo);
		
		theView.setVisible(true);
	}
}