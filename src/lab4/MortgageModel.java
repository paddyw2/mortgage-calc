package lab4;

import java.lang.Math;

/**
* <h1>Mortgage Model</h1>
* This class holds the mortgage data, and performs calculations on this data
* 
* Two main methods:
* 1) calculateMortgageValues: This method takes the values entered by the user and sets
* the variables to the appropriate values using the mortgage formula
* 
* 2) returnScheduleInfo: This method returns a data object filled with either monthly or
* yearly payment information, depending on boolean parameter
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class MortgageModel {
	
	// declare variables
	private double interestFactor;
	private double blendedMonthlyPayment;
	private double totalInterestPaid;
	private double totalInterestPrinciple;
	private double interestPrincipleRatio;
	private double averageInterestYear;
	private double averageInterestMonth;
	private double amortizationYears;
	private double compFreq;
	private double payFreq;
	private double principle;
	private double monthlyPayments;
	
	// uses the interest factor formula to set the current interestFactor
	public void calculateInterestFactor(double interestRate)
	{
		double rate = interestRate / 100.0;
		interestFactor = Math.pow(((rate/compFreq)+1), (compFreq/payFreq)) - 1;
	}
	
	/**
	 * <h1>Calculate Mortgage Values</h1>
	 * 
	 * This method takes five double parameters and uses the mortgage payment formula to update the model
	 * data
	 * 
	 * The method does not return any value, but once it has been called, the model will now hold the
	 * users latest mortgage payment information
	 */
	public void calculateMortgageValues(double payMonths, double principleAmount, double interestRate, double comp, double freq)
	{
		// set variables to parameter values
		monthlyPayments = payMonths;
		principle = principleAmount;
		compFreq = comp;
		payFreq = freq;
		calculateInterestFactor(interestRate);
		// set mortgage payment variables, based on the mortgage formula
		blendedMonthlyPayment = (principle * interestFactor) / (1 - (Math.pow((interestFactor + 1), (monthlyPayments*-1))));
		totalInterestPaid = (blendedMonthlyPayment * monthlyPayments)  - principle;
		totalInterestPrinciple = blendedMonthlyPayment * monthlyPayments;
		interestPrincipleRatio = totalInterestPaid / principle;
		averageInterestYear = totalInterestPaid / (monthlyPayments / 12);
		averageInterestMonth = totalInterestPaid / monthlyPayments;
		amortizationYears = monthlyPayments / 12.0;
	}
	
	// round a double value to two decimal places
	public double round(double value)
	{
		return (Math.round(value * 100.0) / 100.0);
	}
	
	// return mortgage values, rounded to two decimal places
	public double getMonthlyPayment()
	{
		return round(blendedMonthlyPayment);
	}
	
	public double getInterestPaid()
	{
		return round(totalInterestPaid);
	}
	
	public double getInterestPrinciple()
	{
		return round(totalInterestPrinciple);
	}
	
	public double getInterestPrincipleRatio()
	{
		return round(interestPrincipleRatio);
	}
	
	public double getInterestYear()
	{
		return round(averageInterestYear);
	}
	
	public double getInterestMonth()
	{
		return round(averageInterestMonth);
	}
	
	public double getAmmortization()
	{
		return round(amortizationYears);
	}
	
	/**
	 * <h1>Generate Monthly and Yearly Payment Schedule</h1>
	 * 
	 * This method takes a boolean parameter and returns either the yearly
	 * payment schedule, or the monthly payment schedule.
	 * 
	 * It loops through each month and calculates the necessary values, each
	 * time updating the monthly data object. After each year, it also updates
	 * the yearly data object.
	 * 
	 * Once the loop has finished, it then returns one of the objects, depending
	 * on the value of the boolean parameter.
	 */
	public Object[][] returnScheduleInfo(boolean perYear)
	{
		// round up years if uneven to include last years payment on yearly view
		double roundYears = Math.ceil(amortizationYears);
		
		// two data objects, one for monthly, one for yearly
		Object[][] mainData = new String[(int)(roundYears*12)+1][5];
		Object[][] yearlyData = new String[(int)(roundYears)+1][5];
		
		// variables
		double currentTotal = principle;
		double totalPaid = 0;
		double blended = getMonthlyPayment();
		double principlePortion = 0;
		double interest, yearlyPrinciple, yearlyInterest = 0;
		
		// loop through each month, calculating the necessary payment details
		for(int i=0;i<((roundYears*12));i++) {
			
			// monthly interest
			interest = currentTotal * interestFactor;
			// amount paid towards principle
			principlePortion = blended - interest;
			// in the case of last payment, principle may be greater than remaining
			// and to account for rounding errors, if remaining less than $1, set
			// principle to remaining balance
			if ((currentTotal - principlePortion) < 1)
				principlePortion = currentTotal;
			
			totalPaid = totalPaid + principlePortion;
			currentTotal = currentTotal - principlePortion;
			
			// enter months data into monthly object
			mainData[i][0] = String.valueOf(i+1);
			mainData[i][1] = String.valueOf("$" + round(blended));
			mainData[i][2] = String.valueOf("$" + round(interest));
			mainData[i][3] = String.valueOf("$" + round(principlePortion));
			mainData[i][4] = String.valueOf("$" + round(currentTotal));
			
			// increment yearlyInterest variable for the yearly object
			yearlyInterest = yearlyInterest + interest;
			yearlyPrinciple = (blended * 12) - yearlyInterest;
			
			// if month is divisible by 12, then end of year, and so update yearly object
			if (((i+1)%12 == 0 && i > 0)) {
				yearlyData[((i+1)/12) - 1][0] = String.valueOf((i+1)/12);
				yearlyData[((i+1)/12) - 1][1] = String.valueOf("$" + round(blended));
				yearlyData[((i+1)/12) - 1][2] = String.valueOf("$" + round(yearlyInterest));
				yearlyData[((i+1)/12) - 1][3] = String.valueOf("$" + round(yearlyPrinciple));
				yearlyData[((i+1)/12) - 1][4] = String.valueOf("$" + round(currentTotal));
				// reset yearly interest
				yearlyInterest = 0;
			}
		}
		
		// if yearly has been requested, and the amortization period is
		// less than a year, show an error
		if ((amortizationYears < 1) && perYear) {
			mainData[0][0] = "Error";
			return mainData;
		}
		
		// if perYear boolean is asking for yearly data, return it
		// otherwise, return monthly data
		if(perYear)
			return yearlyData;
		else
			return mainData;
	}	
}
