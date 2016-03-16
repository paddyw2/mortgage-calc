import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MortgageController {
	private MortgageView theView;
	private MortgageModel theModel;
	
	public MortgageController(MortgageView theView, MortgageModel theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addCalculationListener(new CalculateListener(theView, theModel));
	}

}