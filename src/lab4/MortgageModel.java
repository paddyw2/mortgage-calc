package lab4;

import java.lang.Math;

public class MortgageModel {
	
	private double interestFactor;
	
	private double blendedMonthlyPayment;
	private double totalInterestPaid;
	private double totalInterestPrinciple;
	private double interestPrincipleRatio;
	private double averageInterestYear;
	private double averageInterestMonth;
	private double ammortizationYears;
	
	private double compFreq;
	private double payFreq;
	
	private double principle;
	
	private double monthlyPayments;
	
	
	public void calculateInterestFactor(double interestRate)
	{
		double rate = interestRate / 100.0;
		interestFactor = Math.pow(((rate/compFreq)+1), (compFreq/payFreq)) - 1;
	}
	
	public void calculateMortgageValues(double payMonths, double principle, double interestRate, double comp, double freq)
	{
		// mortgage calculations, set variables
		monthlyPayments = payMonths;
		this.principle = principle;
		compFreq = comp;
		payFreq = freq;
		calculateInterestFactor(interestRate);
		// set variables
		blendedMonthlyPayment = (principle * interestFactor) / (1 - (Math.pow((interestFactor + 1), (monthlyPayments*-1))));
		totalInterestPaid = (blendedMonthlyPayment * monthlyPayments)  - principle;
		totalInterestPrinciple = blendedMonthlyPayment * monthlyPayments;
		interestPrincipleRatio = totalInterestPaid / principle;
		averageInterestYear = totalInterestPaid / (monthlyPayments / 12);
		averageInterestMonth = totalInterestPaid / monthlyPayments;
		ammortizationYears = monthlyPayments / 12.0;
	}
	
	public double round(double value)
	{
		return (Math.round(value * 100.0) / 100.0);
	}
	
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
		return round(ammortizationYears);
	}
	
	public Object[][] returnScheduleInfo(boolean perYear)
	{
		double roundYears = Math.ceil(ammortizationYears);
		
		Object[][] mainData = new String[(int)(roundYears*12)+1][4];
		Object[][] yearlyData = new String[(int)(roundYears)+1][4];
		double currentTotal = principle;
		double totalPaid = 0;
		double blended = getMonthlyPayment();
		double principlePortion = 0;
		double interest, yearlyPrinciple, yearlyInterest = 0;
		
		if (roundYears < 1) {
			mainData[0][0] = "Error";
			return mainData;
		}
		
		for(int i=0;i<((roundYears*12));i++) {
			
			System.out.println(i+1);
			
			interest = currentTotal * interestFactor;

			principlePortion = blended - interest;
			
			if ((currentTotal - principlePortion) <= 0)
				principlePortion = currentTotal;
			
			totalPaid = totalPaid + principlePortion;
			currentTotal = currentTotal - principlePortion;

			
			mainData[i][0] = String.valueOf("$" + round(blended));
			mainData[i][1] = String.valueOf("$" + round(interest));
			mainData[i][2] = String.valueOf("$" + round(principlePortion));
			mainData[i][3] = String.valueOf("$" + round(currentTotal));
			
			yearlyInterest = yearlyInterest + interest;
			yearlyPrinciple = (blended * 12) - yearlyInterest;
			
			if (((i+1)%12 == 0 && i > 0)) {
				yearlyData[((i+1)/12) - 1][0] = String.valueOf("$" + round(blended));
				yearlyData[((i+1)/12) - 1][1] = String.valueOf("$" + round(yearlyInterest));
				yearlyData[((i+1)/12) - 1][2] = String.valueOf("$" + round(yearlyPrinciple));
				yearlyData[((i+1)/12) - 1][3] = String.valueOf("$" + round(currentTotal));
				
				yearlyInterest = 0;
				
			}
		}
		
		if(perYear)
			return yearlyData;
		else
			return mainData;
	}
	
	
}
