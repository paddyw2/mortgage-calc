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
	
	
	public void calculateInterestFactor(double interestRate)
	{
		double rate = interestRate / 100.0;
		interestFactor = Math.pow(((rate/compFreq)+1), (compFreq/payFreq)) - 1;
	}
	
	public void calculateMortgageValues(double monthlyPayments, double principle, double interestRate, double comp, double freq)
	{
		// mortgage calculations, set variables
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
	
	
}
