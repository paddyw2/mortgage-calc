
public class MVCMortgage {

	public static void main(String[] args)
	{
		MortgageView theView = new MortgageView();
		
		MortgageModel theModel = new MortgageModel();
		
		MortgageController theController = new MortgageController(theView, theModel);
		
		theView.setVisible(true);
	}
}
