package com.bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bank {
	Scanner sc = new Scanner(System.in);
	ArrayList<Account> accounts = new ArrayList<>();

//////////////////////////////////////////////Create Account//////////////////////////////////////////////////////////////

	public void Create_Account() {
		int choice = 0;
		do {
			System.out.println("\t*******Choose Account Type********\n");
			System.out.println("\t+--------------------------------+");
			System.out.println("\t| 1) Saving Account              |");
			System.out.println("\t| 2) Current Account             |");
			System.out.println("\t| 3) Salary Account              |");
			System.out.println("\t| 4) Loan Account                |");
			System.out.println("\t| 5) Exit                        |");
			System.out.println("\t+--------------------------------+");

			System.out.print("\tEnter your choice: ");
			choice = sc.nextInt();
			sc.nextLine(); // Consume newline

			switch (choice) {
			case 1: {
				String holder_name, acc_number;
				double initialbalance;
				System.out.print("\nEnter Account Holder Name: ");
				holder_name = sc.nextLine();
				System.out.print("Enter Account Number: ");
				acc_number = sc.nextLine();
				System.out.print("Enter Initial Balance: ");
				initialbalance = sc.nextDouble();
				sc.nextLine();

				if (initialbalance < Saving_Account.minimum_balance) {
					System.out.println("\nSorry for the Inconvenience\nInitial Balance should be greater than 10000");
				} else {
					accounts.add(new Saving_Account(holder_name, acc_number, initialbalance, new Date()));
					System.out.println("\n***Savings Account Created Successfully***\n");
				}
				break;
			}
			case 2: {
				String holder_name, acc_number;
				double initialbalance;
				System.out.print("\nEnter Account Holder Name: ");
				holder_name = sc.nextLine();
				System.out.print("Enter Account Number: ");
				acc_number = sc.nextLine();
				System.out.print("Enter Initial Balance: ");
				initialbalance = sc.nextDouble();

				accounts.add(new Current_Account(holder_name, acc_number, initialbalance, new Date()));
				System.out.println("\n***Current Account Created Successfully***\n");
				break;
			}
			case 3: {
				String holder_name, acc_number;
				double initialbalance;
				System.out.print("\nEnter Account Holder Name: ");
				holder_name = sc.nextLine();
				System.out.print("Enter Account Number: ");
				acc_number = sc.nextLine();
				System.out.print("Enter Initial Balance: ");
				initialbalance = sc.nextDouble();

				accounts.add(new Salary_Account(holder_name, acc_number, initialbalance, new Date()));
				System.out.println("\n***Salary Account Created Successfully***\n");
				break;
			}
			case 4: {
				String holder_name, acc_number;
				double initialbalance, loanAmount;
				int loanTenureMonths;
				System.out.print("\nEnter Account Holder Name: ");
				holder_name = sc.nextLine();
				System.out.print("Enter Account Number: ");
				acc_number = sc.nextLine();
				System.out.print("Enter Initial Balance: ");
				initialbalance = sc.nextDouble();

				System.out.print("Enter Loan Amount: ");
				loanAmount = sc.nextDouble();
				System.out.print("Enter Loan Tenure (in months): ");
				loanTenureMonths = sc.nextInt();

				double emiAmount = Loan_Account.calculateEMI(loanAmount, Loan_Account.getInterestRate(),
						loanTenureMonths);
				accounts.add(new Loan_Account(holder_name, acc_number, new Date(), initialbalance, loanAmount,
						loanTenureMonths, emiAmount));
				System.out.println("\n***Loan Account Created Successfully***\n");
				break;
			}
			case 5: {
				System.out.println("\nExiting Account Creation Process !!!\n\n");
				return;
			}
			default: {
				System.out.println("\nInvalid Input Entered");
			}
			}
		} while (choice != 5);
	}

////////////////////////////////////////////Freeze Account////////////////////////////////////////////////////////////////

	final static public boolean Freeze(Account account, String accNumber) {

		account.setFreeze(true); // Assuming you have a 'frozen' attribute in the Account class
		account.setAccount_status("Freeze");
		return true;
	}

/////////////////////////////////////////////Search Account//////////////////////////////////////////////////////////////////

	public Account searchAccount(String accNumber) {
		for (int i = 0; i < accounts.size(); i++) {
			Account acc = accounts.get(i);
			if (acc.getAcc_no().equals(accNumber)) {
				return acc;
			}
		}
		return null;
	}

/////////////////////////////////////////////Close Account///////////////////////////////////////////////////////////////

	public void closeAccount(Account account) {
		if (account.getAcc_balance() > 0) {
			System.out.println("Closing account: " + account.getAcc_no());
			System.out.println("Withdrawing remaining balance: " + account.getAcc_balance());
			account.setAcc_balance(0);
		}
		account.setClosingDate(new Date());
		System.out.println("Account closed on: " + account.getClosingDate());
		account.setAccount_status("Closed");
	}

/////////////////////////////////////Daily_Transaction_Report//////////////////////////////////////////////////////////////

	public void Daily_Transaction_Report() {

		System.out.println("+-----------------------------------------+");
		System.out.printf("| %-6s | %-30s |\n", "Choice", "Transaction Report");
		System.out.println("+-----------------------------------------+");
		System.out.printf("| %-6d | %-30s |\n", 1, "Account Withdraw");
		System.out.printf("| %-6d | %-30s |\n", 2, "Account Deposits");
		System.out.printf("| %-6d | %-30s |\n", 3, "Account Total Transactions");
		System.out.printf("| %-6d | %-30s |\n", 4, "Bank Transactions");
		System.out.printf("| %-6d | %-30s |\n", 5, "Exit!!");
		System.out.println("+-----------------------------------------+");
		System.out.println("Choose the type of transaction report:");
		int choice = sc.nextInt();

		switch (choice) {
		case 1: {
			String accountNumber;
			System.out.print("\nEnter the account number: ");
			accountNumber = sc.next();
			Account acc = searchAccount(accountNumber);

			System.out.println("Withdrawal Transactions for Account: " + accountNumber);
			for (int i = 0; i < acc.transactionList.size(); i++) {
				Transaction transaction = acc.transactionList.get(i);
				if (transaction.getProcess_type().equals("Withdrawal")) {
					transaction.display();
				}
			}
			break;
		}
		case 2: {
			String accountNumber;
			System.out.print("\nEnter the account number: ");
			accountNumber = sc.next();
			Account acc = searchAccount(accountNumber);

			System.out.println("Deposit Transactions for Account: " + accountNumber);
			for (int i = 0; i < acc.transactionList.size(); i++) {
				Transaction transaction = acc.transactionList.get(i);
				if (transaction.getProcess_type().equals("Deposit")) {
					transaction.display();
				}
			}
			break;
		}
		case 3:
			System.out.print("Enter the account number: ");
			String accountNumber = sc.next();
			Account acc = searchAccount(accountNumber);
			if (acc != null) {
				System.out.println("All Transactions for Account: " + accountNumber);
				for (Transaction transaction : acc.transactionList) {
					transaction.display();
				}
			} else {
				System.out.println("Account not found.");
			}
			break;
		case 4:
		    System.out.println("Bank Transaction Record\n");

		    // Prompt user to enter the account number
		    System.out.print("Enter the account number: ");
		    String accountNumber1 = sc.next();
		    Account acc1 = searchAccount(accountNumber1);
		    if (acc1 != null) {
		        System.out.println("Account Number: " + acc1.getAcc_no());
		        if (acc1.transactionList != null) {
		            for (int j = 0; j < acc1.transactionList.size(); j++) {
		                Transaction transaction = acc1.transactionList.get(j);
		                transaction.display();
		            }

		            // Check if the last transaction date is before two months ago
		            Date twoMonthsAgo = new Date(System.currentTimeMillis() - (long) 60 * 24 * 60 * 60 * 1000);
		            if (acc1.getLastTransaction().before(twoMonthsAgo)) {
		                System.out.println("Account " + acc1.getAcc_no()
		                        + " has been inactive for more than two months. Freezing account...");
		                Freeze(acc1, accountNumber1); // Call the freeze function
		            }
		        }
		    } else {
		        System.out.println("Account not found.");
		    }
		    break;

		default:
			System.out.println("Invalid choice");
			break;
		}
	}

///////////////////////////////////////////////////Main//////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Bank bank = new Bank();
		int choice;
		do {
			try {
				System.out.println("\n+===========================================+");
				System.out.println("|     Welcome to Account Management System  |");
				System.out.println("+===========================================+");
				System.out.println("| 1. Create New Account                     |");
				System.out.println("+-------------------------------------------+");
				System.out.println("| 2. Counter Activity                       |");
				System.out.println("+-------------------------------------------+");
				System.out.println("| 3. Search Account                         |");
				System.out.println("+-------------------------------------------+");
				System.out.println("| 4. Close Account                          |");
				System.out.println("+-------------------------------------------+");
				System.out.println("| 5. Daily Transaction Report               |");
				System.out.println("+-------------------------------------------+");
				System.out.println("| 6. View Account Lifecycle                 |");
				System.out.println("+-------------------------------------------+");
				System.out.println("| 7. Interest Calculation                   |");
				System.out.println("+-------------------------------------------+");
				System.out.println("| 8. Display Account Details                |");
				System.out.println("+-------------------------------------------+");
				System.out.println("| 9. Exit                                   |");
				System.out.println("+-------------------------------------------+");

				System.out.print("\nEnter your choice: ");
				choice = sc.nextInt();
				// sc.nextLine();

				switch (choice) {
				case 1: {
					bank.Create_Account();
					break;
				}
				case 2: {
					// Counter Activity: Deposit, Withdraw, Transfer, Repay EMI
					System.out.println("\nCounter Activity");
					System.out.println("+-----------------------------------+");
					System.out.println("| 1. Deposit                        |");
					System.out.println("| 2. Withdraw                       |");
					System.out.println("| 3. Transfer                       |");
					System.out.println("| 4. Repay EMI                      |");
					System.out.println("+-----------------------------------+");
					System.out.print("\nEnter your choice: ");
					int counterChoice = sc.nextInt();
					switch (counterChoice) {
					case 1: {
						// Deposit
						System.out.print("\nEnter account number: ");
						String accNumberDeposit = sc.next();
						System.out.print("Enter deposit amount: ");
						double depositAmount = sc.nextDouble();
						Account accDeposit = bank.searchAccount(accNumberDeposit);
						if (accDeposit != null) {
							accDeposit.Deposit(accDeposit, accNumberDeposit, depositAmount);
						} else {
							System.out.println("Account not found.");
						}
						break;
					}
					case 2: {
						// Withdraw
						System.out.print("\nEnter account number: ");
						String accNumberWithdraw = sc.next();
						System.out.print ("Enter withdrawal amount: ");
						double withdrawAmount = sc.nextDouble();
						Account accWithdraw = bank.searchAccount(accNumberWithdraw);
						if (accWithdraw != null) {
							accWithdraw.withdraw(accWithdraw, accNumberWithdraw, withdrawAmount);
						} else {
							System.out.println("Account not found.");
						}
						break;
					}
					case 3: {
						// Transfer
						System.out.println("\nEnter source account number: ");
						String sourceAccNumber = sc.next();
						System.out.println("Enter destination account number: ");
						String destinationAccNumber = sc.next();

						Account sourceAcc = bank.searchAccount(sourceAccNumber);
						Account destinationAcc = bank.searchAccount(destinationAccNumber);

						if (sourceAcc != null && destinationAcc != null) {
							System.out.println("Enter amount to transfer: ");
							double transferAmount = sc.nextDouble();
							sourceAcc.FundTransfer(sourceAcc, sourceAccNumber, destinationAcc, destinationAccNumber,
									transferAmount);
						} else {
							if (sourceAcc == null) {
								System.out.println("Source Account Number not Found !!");
							}
							if (destinationAcc == null) {
								System.out.println("Destination Account Number not Found !!");
							}
						}
						break;
					}
					case 4: {
						// Repay EMI
						System.out.println("\nEnter account number to repay EMI: ");
						String accNumberRepay = sc.next();
						Account accRepay = bank.searchAccount(accNumberRepay);
						if (accRepay != null && accRepay instanceof Loan_Account) {
							// Cast the account to Loan_Account type
							Loan_Account loanAcc = (Loan_Account) accRepay;
							loanAcc.repayEMI();
						} else {
							System.out.println("Account not found or not a loan account.");
						}
						break;
					}
					}
					break;
				}

				case 3: {
					// Search Account
					System.out.println("\nEnter account number to search: ");
					String accNumberToSearch = sc.next();
					Account searchedAccount = bank.searchAccount(accNumberToSearch);
					if (searchedAccount != null) {
						searchedAccount.display();
					} else {
						System.out.println("Account not found.");
					}
					break;
				}
				case 4: {
					// Close Account
					System.out.println("\nEnter account number to close: ");
					String accNumberToClose = sc.next();
					Account accToClose = bank.searchAccount(accNumberToClose);
					if (accToClose != null) {
						accToClose.display();
						bank.closeAccount(accToClose);
					} else {
						System.out.println("Account not found.");
					}
					break;
				}
				case 5: {
					// Daily Transaction Report

					bank.Daily_Transaction_Report();

					break;
				}
				case 6: {
					// View Account Lifecycle
					System.out.print("\nEnter the account number: ");
					String AccountNumber = sc.next();
					Account acc1 = bank.searchAccount(AccountNumber);
					if (acc1 != null) {
						Account.Account_Lifecycle(acc1, AccountNumber);
					} else {
						System.out.println("Account not found.");
					}
					break;
				}
				case 7: {
					// Interest Calculation
					System.out.print("\nEnter the account number: ");
					String interestAccountNumber = sc.next();
					Account interestAcc = bank.searchAccount(interestAccountNumber);
					if (interestAcc != null) {
						interestAcc.Interest_Calculation(interestAcc, interestAccountNumber);
					} else {
						System.out.println("Account not found.");
					}
					break;
				}
				case 8: {
					System.out.print("\nEnter account number to display details: ");
					String accNumberToDisplay = sc.next();
					sc.nextLine();
					Account accToDisplay = bank.searchAccount(accNumberToDisplay);
					if (accToDisplay != null) {
						accToDisplay.display();
					} else {
						System.out.println("Account not found.");
					}

					break;
				}
				case 9: {

					System.out.println("Are you sure you want to exit? (1 for yes, 2 for no): ");
					int exitChoice = sc.nextInt();
					if (exitChoice == 1) {
						System.out.println("Exiting...");
						return;
					}
					break;
				}
				default:
					System.out.println("Invalid choice");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nError: Invalid input. Please enter a valid number.");
				sc.nextLine();
				choice = 0;
			} catch (NoSuchElementException e) {
				System.out.println("\nError: Input not found.");
				sc.nextLine();
				choice = 0;
			} catch (NullPointerException e) {
				System.out.println("\nError: NullPointerException occurred.");
				choice = 0;
			} catch (Exception e) {
				System.out.println("\nAn unexpected error occurred: " + e.getMessage());
				choice = 0;
			}
		} while (choice != 9);

		sc.close();
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////