package com.bank;

import java.util.Date;
import java.util.Scanner;

///////////////////////////////////////////Attribute////////////////////////////////////////////////////

public class Current_Account extends Account {
	final String Account_type = "Current Account";
	private static double overdraft_limit = 24000;
	Scanner sc = new Scanner(System.in);

//////////////////////////////////////////Constructor////////////////////////////////////////////////////

	public Current_Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Current_Account(String holder_Name, String acc_no, Double acc_balance, Date openingDate) {
		super(holder_Name, acc_no, openingDate, acc_balance);
		// TODO Auto-generated constructor stub
	}

///////////////////////////////////////////Getter Setter////////////////////////////////////////////////////

	public static double getOverdraft_limit() {
		return overdraft_limit;
	}

	public static void setOverdraft_limit(double overdraft_limit) {
		Current_Account.overdraft_limit = overdraft_limit;
	}

///////////////////////////////////////Interest_Calculation////////////////////////////////////////////////////	

	@Override
	public void Interest_Calculation(Account a, String b) {
		// TODO Auto-generated method stub
		System.out.println("\nSorry for the inconvenience!!\nCurrent Account doesn't provide any interest");

	}

///////////////////////////////////////////Withdraw////////////////////////////////////////////////////

	@Override
	public void withdraw(Account acc, String Acc_number, double amount) {
//		Account acc = searchAccount(Acc_number);
		if (acc != null) {
			if (!acc.isFreeze()) { // Check if the account is not frozen
				if (acc.getClosingDate() == null) {
					if (acc.getAcc_balance() >= amount) {
						acc.setAcc_balance(acc.getAcc_balance() - amount);
						System.out.println("\n***Withdrawal successful***");
				
						Transaction transaction = new Transaction(Acc_number, new Date(), "Withdrawal", amount, null,
								acc_balance);
						acc.transactionList.add(transaction);
						acc.setLastTransaction(new Date());
						System.out.println("\nTransaction recorded successfully:");
					} else if (acc.getAcc_balance() < amount) {
						overDraft(acc, amount);
					} else {
						System.out.println("Insufficient funds.");
					}
				} else {
					System.out
							.println("\nAccount with Account Number " + Acc_number + " is closed. Withdrawal failed.");
				}
			} else {
				System.out.println("\nCannot withdraw from a frozen account.");
			}
		} else {
			System.out.println("\nAccount with Account Number " + Acc_number + " not found. Withdrawal failed.");
		}
	}

///////////////////////////////////////////////Over Draft/////////////////////////////////////////////////////////

	private void overDraft(Account acc, double amount) {
		int choice;
		System.out.println("\nDo you want to take Overdraft Facility?");
		System.out.println("+-------------------------------------------+");
		System.out.println("|              Overdraft Facility           |");
		System.out.println("+-------------------------------------------+");
		System.out.println("|     1    |              YES               |");
		System.out.println("|     2    |              NO                |");
		System.out.println("+-------------------------------------------+");
		System.out.print("Enter Your Choice: ");
		choice = sc.nextInt();

		if (choice == 1) {
			System.out.println("\nYour Over-Draft Limit is:  " + Current_Account.overdraft_limit);
			acc.setAcc_balance(acc.getAcc_balance() + Current_Account.overdraft_limit);
			if (acc.getAcc_balance() >= amount) {
				acc.setAcc_balance(acc.getAcc_balance() - amount);
				setLastTransaction(new Date());
				System.out.println("\nWithdrawal successful! New balance: " + acc.getAcc_balance());
			} else {
				System.out.println("\nInsufficient funds.");
			}
		} else if (choice == 2) {
			System.out.println("\nInsufficient Balance Withdrawal Transction Failed.");
		}
	}

//////////////////////////////////////////////////////Display///////////////////////////////////////////////////////////////

	public void display() {
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("|                        Account Details                          |\n");
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Account Type", Account_type);
		System.out.println("+-----------------------------------------------------------------+");
		super.display();
		System.out.printf("| %-30s | %-30s |\n", "Overdraft Limit", overdraft_limit);
		System.out.println("+-----------------------------------------------------------------+");
	}

}

////////////////////////////////////////////////////////////////////////////////////////////////////////
