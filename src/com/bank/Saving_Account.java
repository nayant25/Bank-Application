package com.bank;

import java.util.Date;
import java.util.Scanner;

////////////////////////////////////////////Attribute////////////////////////////////////////////////////

public class Saving_Account extends Account {
	final String Account_type = "Saving Account";
	static double minimum_balance = 10000;
	private static double interestRate = 0.07;
	Scanner scanner = new Scanner(System.in);

////////////////////////////////////////////Constructor////////////////////////////////////////////////////

	public Saving_Account() {
		super();
	}

	public Saving_Account(String holder_Name, String acc_no, Double acc_balance, Date openingDate) {
		super(holder_Name, acc_no, openingDate, acc_balance);

	}

////////////////////////////////////////////Getter Setter//////////////////////////////////////////////

	public static double getMinimum_balance() {
		return minimum_balance;
	}

	public static void setMinimum_balance(double minimum_balance) {
		Saving_Account.minimum_balance = minimum_balance;
	}

	public static double getAnnual_interest() {
		return interestRate;
	}

	public static void setAnnual_interest(double interest_Rate) {
		Saving_Account.interestRate = interest_Rate;
	}

///////////////////////////////////////////Withdraw////////////////////////////////////////////////////

	@Override
	public void withdraw(Account acc, String Acc_number, double amount) {
//		 = searchAccount(Acc_number);
		if (acc != null) {
			if (!acc.isFreeze()) {
				if (acc.getClosingDate() == null) {
					if ((acc.getAcc_balance() - amount) >= minimum_balance) {
						acc.setAcc_balance(acc.getAcc_balance() - amount);
						System.out.println("\n***Withdrawal successful***");
						Transaction transaction = new Transaction(Acc_number, new Date(), "Withdrawal", amount, null,
								acc_balance);
						acc.transactionList.add(transaction);
						acc.setLastTransaction(new Date());
						System.out.println("\nTransaction recorded successfully:");

					} else {
						System.out.println("\nInsufficient funds. Minimum balance of Rs. 10,000 must be maintained.");
					}
				} else {
					System.out
							.println("\nAccount with Account Number " + Acc_number + " is closed. Withdrawal failed.");
				}
			} else {
				System.out.println("\nCannot withdraw from a frozen account.");
			}
		} else {
			System.out.println("\nAccount with Account Number " + Acc_number + " was not found in the database.");
		}
	}

/////////////////////////////////////Interest Calculation/////////////////////////////////////////////

	public void Interest_Calculation(Account account, String account_Number) {

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
			interest = account.getAcc_balance() * interestRate;
			Acc_balance += interest; 
			System.out.println("\nInterest calculated and added to the account (per month)!! Current balance: "
					+ Acc_balance);
			break;
		default:
			System.out.println("Invalid choice.");
		}

	}

/////////////////////////////////////////////////Display////////////////////////////////////////////////////////
	public void display() {
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("|                        Account Details                          |\n");
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Account Type", Account_type);
		System.out.println("+-----------------------------------------------------------------+");
		super.display();
		System.out.printf("| %-30s | %-30s |\n", "Annual Interest Rate", interestRate);
		System.out.println("+-----------------------------------------------------------------+");
	}

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
