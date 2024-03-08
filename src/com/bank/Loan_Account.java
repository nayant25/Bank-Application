package com.bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

///////////////////////////////////////Attribute//////////////////////////////////////////////////////////

public class Loan_Account extends Account {
	final String Account_type = "Loan Account";
	private double loanAmount;
	private static double interestRate = 0.08;
	private int loanTenureMonths;
	private double emiAmount;
	private int remainingEmis;
	Scanner scanner = new Scanner(System.in);

////////////////////////////////////////Constructor//////////////////////////////////////////////////////

	public Loan_Account(String holder_Name, String acc_no, Date openingDate, Double acc_balance, double loanAmount,
			int loanTenureMonths, double emiAmount) {
		super(holder_Name, acc_no, openingDate, acc_balance);
		this.loanAmount = loanAmount;
		this.loanTenureMonths = loanTenureMonths;
		this.emiAmount = Loan_Account.calculateEMI(loanAmount, interestRate, loanTenureMonths);
		this.remainingEmis = loanTenureMonths;
		this.transactionList = new ArrayList<>();
	}

/////////////////////////////////////////Getter Setter///////////////////////////////////////////////////

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public static double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		Loan_Account.interestRate = interestRate;
	}

	public int getLoanTenureMonths() {
		return loanTenureMonths;
	}

	public void setLoanTenureMonths(int loanTenureMonths) {
		this.loanTenureMonths = loanTenureMonths;
	}

	public double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public int getRemainingEmis() {
		return remainingEmis;
	}

	public void setRemainingEmis(int remainingEmis) {
		this.remainingEmis = remainingEmis;
	}

////////////////////////////////////////Calculate EMI////////////////////////////////////////////////////

	// Calculate EMI based on loan amount, interest rate, and tenure
	public static double calculateEMI(double loanAmount, double interestRate, int loanTenureMonths) {
		double monthlyInterestRate = interestRate / 12 / 100;
		double emi = loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTenureMonths)
				/ (Math.pow(1 + monthlyInterestRate, loanTenureMonths) - 1);
		return emi;
	}

//////////////////////////////////////////Repay EMI/////////////////////////////////////////////////////

	public void repayEMI() {
		if (remainingEmis > 0) {
			double interestAmount = interestRate / 12 / 100;
			double totalRepayment = emiAmount + interestAmount;
			remainingEmis--;
			System.out.println("\nRepayment Details:");
			System.out.println("+----------------------------------------------+");
			System.out.printf("| %-14s| %-14s| %-14s| %-14s|%n", "EMI Amount", "Interest Amount", "Total Repayment", "Remaining EMIs");
			System.out.println("+----------------------------------------------+");
			System.out.printf("| %-14.2f| %-14.2f| %-14.2f| %-14d|%n", emiAmount, interestAmount, totalRepayment, remainingEmis);
			System.out.println("+----------------------------------------------+");

			setLastTransaction(new Date());
		} else {
			System.out.println("No remaining EMIs.");
		}
	}

/////////////////////////////////////Interest Calculation/////////////////////////////////////////////

	@Override
	public void Interest_Calculation(Account account, String accountNumber) {

		System.out.println("\n\nSelect the interest calculation period:");
		System.out.println("+--------------------------------------------+");
		System.out.println("|        Interest Calculation Period         |");
		System.out.println("+--------------------------------------------+");
		System.out.println("|     1    |           Per month             |");
		System.out.println("|     2    |           Per year              |");
		System.out.println("+--------------------------------------------+");
		System.out.print("Enter your choice (1/2): ");
		int choice = scanner.nextInt();
		double interest;

		if (account != null && account.getAcc_no().equals(accountNumber)) {
			double Acc_balance = 0;
			switch (choice) {
			case 1:
				double monthlyInterestRate = interestRate / 12;
				interest = account.getAcc_balance() * monthlyInterestRate;
				Acc_balance  += interest; 
				System.out.println("\nInterest calculated and added to the account (per month)!! Current balance: "
						+ Acc_balance);
				break;
			case 2:
				interest = account.getAcc_balance() * interestRate/365;
				Acc_balance += interest; 
				System.out.println("\nInterest calculated and added to the account (per month)!! Current balance: "
						+ Acc_balance);
				break;
			default:
				System.out.println("\nInvalid choice.");
			}
		} else {
			System.out.println("Account with account number " + accountNumber + " not found.");
		}

	}

/////////////////////////////////////////////////Withdraw/////////////////////////////////////////////////////////////////////

	@Override
	public void withdraw(Account acc, String Acc_number, double amount) {
		// TODO Auto-generated method stub
		System.out.println("\nWithdraw can't be done in Loan Account");
	}

/////////////////////////////////////////////////Display/////////////////////////////////////////////////////////////////////

	public void display() {
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("|                        Account Details                          |\n");
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Account Type", Account_type);
		System.out.println("+-----------------------------------------------------------------+");
		super.display();
		System.out.printf("| %-30s | %-30s |\n", "Loan Amount", loanAmount);
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Interest Rate", interestRate + "%");
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Loan Tenure (months)", loanTenureMonths);
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Remaining EMIs", remainingEmis);
		System.out.println("+-----------------------------------------------------------------+");
	}

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
