package com.bank;

import java.util.Date;
import java.util.UUID;

public class Transaction {

	private String acc_Number;
	private Date transaction_date;
	private String process_type;
	private double transaction_amount;
	private String transaction_Id;
	private double acc_balance;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(String acc_Number, Date transaction_date, String process_type, double amount,
			String transaction_Id, double acc_balance) {
		super();
		this.acc_Number = acc_Number;
		this.transaction_date = transaction_date;
		this.process_type = process_type;
		this.transaction_amount = amount;
		this.transaction_Id = generateTransactionId();
		this.acc_balance = acc_balance;

	}

	public double getAcc_balance() {
		return acc_balance;
	}

	public void setAcc_balance(double acc_balance) {
		this.acc_balance = acc_balance;
	}

	public String getAcc_Number() {
		return acc_Number;
	}

	public void setAcc_Number(String acc_Number) {
		this.acc_Number = acc_Number;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getProcess_type() {
		return process_type;
	}

	public void setProcess_type(String process_type) {
		this.process_type = process_type;
	}

	public double getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public String getTransaction_Id() {
		return transaction_Id;
	}

	// Method to generate a unique 11-digit transaction ID
	private String generateTransactionId() {
		String uuid = UUID.randomUUID().toString().replace("-", ""); // Remove hyphens
		return uuid.substring(0, 11); // Take the first 11 characters
	}

	public void display() {
		System.out.println(
				"+-----------------------------------------------------------------------------------------------------------------------------+");
		System.out.printf("| %-15s | %-15s | %-18s | %-17s | %-28s | %-15s |\n", "Transaction Id", "Account Number",
				"Transaction Amount", "Process Type", "Transaction Date", "Current Balance");
		System.out.println(
				"+-----------------------------------------------------------------------------------------------------------------------------+");
		System.out.printf("| %-15s | %-15s | %-18.1f | %-17s | %-28s | %-15.1f |\n", transaction_Id, acc_Number,
				transaction_amount, process_type, transaction_date, acc_balance);
		System.out.println(
				"+-----------------------------------------------------------------------------------------------------------------------------+");
	}

}
