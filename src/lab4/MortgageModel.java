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
	private double interestRate;
	
	
	public void calculateInterestFactor(double interestRate)
	{
		double rate = interestRate / 100.0;
		interestFactor = Math.pow(((rate/compFreq)+1), (compFreq/payFreq)) - 1;
	}
	
	public void calculateMortgageValues(double monthlyPayments, double principle, double interestRate, double comp, double freq)
	{
		// mortgage calculations, set variables
		this.principle = principle;
		this.interestRate = interestRate;
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
	
	public Object[][] returnScheduleInfo()
	{
		Object[][] data = new String[(int)ammortizationYears+1][4];
		double currentTotal = principle;
		double totalPaid = 0;
		double blended = getMonthlyPayment();
		double paidPerYear = blended * 12;
		double principlePortion = 0;
		double interest = 0;
		
		for(int i=0;i<(ammortizationYears + 1);i++) {
			//totalPaid = principle - (getMonthlyPayment()*((i+1)*12));
			
			System.out.println(currentTotal);
			System.out.println(i);
			
			
			interest = currentTotal * (interestRate / 100);
			
			System.out.println(interest);
			
	
			
			if (i == ammortizationYears) {
				principlePortion = currentTotal;
				totalPaid = totalPaid + principlePortion;
				currentTotal = currentTotal - principlePortion;
			} else {
				principlePortion = paidPerYear - interest;
				totalPaid = totalPaid + principlePortion;
				currentTotal = currentTotal - principlePortion;
				
			}
				
			
			data[i][0] = String.valueOf("$" + round(blended));
			data[i][1] = String.valueOf("$" + round(interest));
			data[i][2] = String.valueOf("$" + round(principlePortion));
			data[i][3] = String.valueOf("$" + round(currentTotal));
			
			
			
		}
		
		return data;
	}
	
	
}
