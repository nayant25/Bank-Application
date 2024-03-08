package com.bank;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

/////////////////////////////////////////////Attribute//////////////////////////////////////////////////////

public abstract class Account {

	String Holder_Name;
	String Acc_no;
	Date openingDate;
	double acc_balance;
	String acc_number;
	boolean freeze = false;
	Date LastTransaction = null;
	Scanner sc = new Scanner(System.in);
	ArrayList<Account> accounts = new ArrayList<>();
	List<Transaction> transactionList;
	Date closingDate = null;
	String account_status;

////////////////////////////////////////////Constructor////////////////////////////////////////////////////

	public Account() {
		this.transactionList = new ArrayList<>();
	}

	public Account(String holder_Name, String acc_no, Date openingDate, Double acc_balance) {
		Holder_Name = holder_Name;
		Acc_no = acc_no;
		this.openingDate = new Date();
		this.acc_balance = acc_balance;
		this.transactionList = new ArrayList<>();
		this.account_status = "active";
	}

///////////////////////////////////////////Getter Setter//////////////////////////////////////////////

	public String getAccount_status() {
		return account_status;
	}

	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}

//	public boolean isFreeze() {
//		return freeze;
//	}
	
	

	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}

	public boolean isFreeze() {
		return freeze;
	}

	public String getHolder_Name() {
		return Holder_Name;
	}

	public void setHolder_Name(String holder_Name) {
		this.Holder_Name = holder_Name;
	}

	public String getAcc_no() {
		return Acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.Acc_no = acc_no;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public double getAcc_balance() {
		return acc_balance;
	}

	public void setAcc_balance(double acc_balance) {
		this.acc_balance = acc_balance;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Date getLastTransaction() {
		return LastTransaction;
	}

	public void setLastTransaction(Date LastTransaction) {
		this.LastTransaction = LastTransaction;
	}

////////////////////////////////////////////////DEPOSIT//////////////////////////////////////////////////

	final public void Deposit(Account acc, String Acc_number, double amount) {
//		Account acc = searchAccount(Acc_number);
		if (acc != null) {
			if (amount <= 0) {
				System.out.println("Invalid deposit amount.");
			} else {
				if (acc.isFreeze()) {
					System.out.println("Cannot deposit to a frozen account.");
				} else if (acc.getAccount_status().equals("Closed")) {
					System.out.println("Cannot deposit to a closed account.");
				} else {
					acc.setAcc_balance(acc.getAcc_balance() + amount);
					Transaction transaction = new Transaction(Acc_number,new Date(),"Deposit",amount,null,acc_balance);
					acc.transactionList.add(transaction);
					acc.setLastTransaction(new Date());
					System.out.println("\n***Amount Deposited successfully***");
				}
			}
		} else {
			System.out.println("\nAccount Number not Found !!");
		}

	}

////////////////////////////////////////////Fund Transfer////////////////////////////////////////////////

	final public void FundTransfer(Account fromAcc, String fromAccNumber, Account toAcc, String toAccNumber,
			double amount) {
//		Account fromAcc = searchAccount(fromAccNumber);
//		Account toAcc = searchAccount(toAccNumber);

		if (fromAcc == null) {
			System.out.println("\n\nSource Account Number not Found !!");
		} else if (toAcc == null) {
			System.out.println("Destination Account Number not Found !!");
		} else {
			if (amount <= 0) {
				System.out.println("Invalid transfer amount.");
			} else {
				if (!fromAcc.isFreeze() && !fromAcc.getAccount_status().equals("Closed")) {
					if (!toAcc.isFreeze() && !toAcc.getAccount_status().equals("Closed")) {
						if (fromAcc.getAcc_balance() >= amount) {
							fromAcc.setAcc_balance(fromAcc.getAcc_balance() - amount);
							toAcc.setAcc_balance(toAcc.getAcc_balance() + amount);

							// Update transaction details
							Transaction fromTransaction = new Transaction();
							fromTransaction.setAcc_Number(fromAccNumber);
							fromTransaction.setTransaction_amount(-amount); // Negative amount for withdrawal
							fromTransaction.setProcess_type("Transfer to " + toAccNumber);
							fromTransaction.setTransaction_date(new Date());
							fromTransaction.setAcc_balance(acc_balance);
							fromAcc.transactionList.add(fromTransaction);

							Transaction toTransaction = new Transaction();
							toTransaction.setAcc_Number(toAccNumber);
							toTransaction.setTransaction_amount(amount);
							toTransaction.setProcess_type("Transfer from " + fromAccNumber);
							toTransaction.setTransaction_date(new Date());
							toTransaction.setAcc_balance(acc_balance);
							toAcc.transactionList.add(toTransaction);
							toAcc.setLastTransaction(new Date());
							fromAcc.setLastTransaction(new Date());

							System.out.println("\n***Transfer successful***");
						} else {
							System.out.println("\nInsufficient funds in the source account.");
						}
					} else {
						System.out.println("\nCannot transfer to a frozen or closed destination account.");
					}
				} else {
					System.out.println("\nCannot transfer from a frozen or closed source account.");
				}
			}
		}

	}

/////////////////////////////////////////Account Lifecycle////////////////////////////////////////

	public final static void Account_Lifecycle(Account account, String accountNumber) {
	    if (account != null) {
	        System.out.println("+------------------------------------------------------------------+");
	        System.out.printf( "|                         Account Lifecycle                        |\n");
	        System.out.println("+------------------------------------------------------------------+");
	        System.out.printf("| %-30s | %-31s |\n", "Account Number", account.getAcc_no());
	        System.out.println("+------------------------------------------------------------------+");
	        System.out.printf("| %-30s | %-31s |\n", "Account Holder Name", account.getHolder_Name());
	        System.out.println("+------------------------------------------------------------------+");
	        System.out.printf("| %-30s | %-31s |\n", "Opening Date", account.getOpeningDate());
	        System.out.println("+------------------------------------------------------------------+");

	        if (account.getClosingDate() != null) {
	            System.out.printf("| %-30s | %-31s |\n", "Account was closed on", account.getClosingDate());
	            System.out.println("+------------------------------------------------------------------+");
	            System.out.printf("| %-30s | %-31s |\n", "Account Status", "Closed");
	            System.out.println("+------------------------------------------------------------------+");
	        } else {
	            
	            System.out.printf("| %-30s | %-31s |\n", "Current Balance", account.getAcc_balance());
	            System.out.println("+------------------------------------------------------------------+");
	            System.out.printf("| %-30s | %-31s |\n", "Last Transaction Date", account.getLastTransaction());
	            System.out.println("+------------------------------------------------------------------+");
	            System.out.printf("| %-30s | %-31s |\n", "Account Status", account.getAccount_status());
	            System.out.println("+------------------------------------------------------------------+");
	        }
	    } else {
	        System.out.println("\nAccount with account number " + accountNumber + " not found.");
	    }
	}


/////////////////////////////////////////////Display/////////////////////////////////////////////////////////////////////

	public void display() {
		System.out.printf("| %-30s | %-30s |\n", "Holder Name", Holder_Name);
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Account Number", Acc_no);
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Opening Date", openingDate);
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Current Balance", acc_balance);
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Account Status", account_status);
		System.out.println("+-----------------------------------------------------------------+");
		System.out.printf("| %-30s | %-30s |\n", "Last Transaction Date", LastTransaction);
		System.out.println("+-----------------------------------------------------------------+");
		if (closingDate != null) {
			System.out.printf("| %-30s | %-30s |\n", "Closing Date", closingDate);
			System.out.println("+-----------------------------------------------------------------+");
		}

		if (freeze) {
			System.out.printf("| %-30s | %-30s |\n", "Account Status", "Account is Frozen");
			System.out.println("+-----------------------------------------------------------------+");
		}
	}

////////////////////////////////////////////Abstract Methods//////////////////////////////////////////////////////////

	public abstract void Interest_Calculation(Account account, String account_Number);

	public abstract void withdraw(Account acc, String Acc_number, double amount);

}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
