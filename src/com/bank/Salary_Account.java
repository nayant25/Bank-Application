package com.bank;

import java.util.Date;
import java.util.Scanner;

/////////////////////////////////////////Attribute//////////////////////////////////////////////////////
public class Salary_Account extends Account {
	final String Account_type = "Salary Account";
	private static double annual_interest = 0.04;
	Scanner scanner = new Scanner(System.in);

	public Salary_Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salary_Account(String holder_Name, String acc_no, Double acc_balance, Date openingDate) {
		super(holder_Name, acc_no, openingDate, acc_balance);

	}

/////////////////////////////////////////withdrawal///////////////////////////////////////////////////////////

	@Override
	public void withdraw(Account acc, String accNumber, double amount) {
			if (acc != null) {
				if (!acc.isFreeze()) {
				if (acc.getClosingDate() == null) {
					acc.setAcc_balance(acc.getAcc_balance() - amount);
					System.out.println("\nWithdrawal successful! New balance: " + acc.getAcc_balance());
					acc.setLastTransaction(new Date());
					Transaction transaction = new Transaction(acc_number, new Date(), "Withdrawal", amount, null,
							acc_balance);
					acc.transactionList.add(transaction);
					System.out.println("\nTransaction recorded successfully:");
					transaction.display();

				} else {
					System.out.println("\nAccount with Account Number " + accNumber + " is closed. Withdrawal failed.");
				}
			} else {
				System.out.println("\nSorry!! Account Not Found in database.");
			}
		} else {
			System.out.println("\nAccount is frozen. Withdrawal not allowed.");
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
				double monthlyInterestRate = annual_interest / 12;
				interest = account.getAcc_balance() * monthlyInterestRate;
				Acc_balance  += interest; 
				System.out.println("\nInterest calculated and added to the account (per month)!! Current balance: "
						+ Acc_balance);
				break;
			case 2:
				interest = account.getAcc_balance() * annual_interest;
				Acc_balance += interest; 
				System.out.println("\nInterest calculated and added to the account (per month)!! Current balance: "
						+ Acc_balance);
				break;
			default:
				System.out.println("Invalid choice.");
			}
		} else {
			System.out.println("\nAccount with account number " + accountNumber + " not found.");
		}

	}

////////////////////////////////////////////////////////Display//////////////////////////////////////////////////////////////////////

	public void display() {
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("|                        Account Details                          |\n");
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Account Type", Account_type);
		System.out.println("+-----------------------------------------------------------------+");
		super.display();
		System.out.printf("| %-30s | %-30s |\n", "Annual Interest Rate", annual_interest);
		System.out.println("+-----------------------------------------------------------------+");
	}

}

////////////////////////////////////////////////////////////////////////////////////////////////////////
