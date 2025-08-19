package com.ATM.atmapplication;
import com.ATM.atmapplication.exceptions.InsufficentFundsException;
import java.util.*;
import java.io.Serializable;

public class Accounts implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String location;
	private String accnumber;
	private int pin;
	private double balance;
	
	Accounts(){
		
	}
	Accounts(String Name,String area,String accnum,int pin,double amount){
		this.username = Name;
		this.location = area;
		this.pin = pin;
		this.accnumber = accnum;
		setBalance(amount);
	}
	
	public String getName() {
		return username;
	}
	
	public String getAccnum() {
		return accnumber;
	}
	
	public String getlocation() {
		return location;
	}
	
	public int getPin() {
		return pin;
	}
	
	public void setBalance(double amount) {
		Scanner sc = new Scanner(System.in);
		while(amount<=100) {
			System.out.println("Please enter the a valid number above 100.");
			amount = sc.nextDouble();
		}
		this.balance = amount;
	}
	
	public void setaccountNumber(String accountnumber) {
		this.accnumber = accountnumber;
	}
	
	public double getBalance() {
		return balance;
	}
	public void withdraw(double amount) throws InsufficentFundsException{
		if(amount < getBalance()) {
			balance -= amount;
			System.out.println(amount);
		}
		else if(amount == getBalance()) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Your withdraw amount is same as ur balance do u want to withdraw ?\nIf yes click --> 1\nIf no click --> 2");
			int ans = sc.nextInt();
			switch(ans) {
			case 1:
				balance -= amount;
				System.out.println(amount);
				break;
			case 2:
				System.out.println("Withdrawal is cancelled");
				break;
			}
		}
		else {
		    throw new InsufficentFundsException(
		        "Withdrawal failed: Entered amount ₹" + amount +
		        " exceeds available balance ₹" + getBalance()
		    );
		}

	}
	public void details() {
		System.out.println();
		System.out.println("The username is:- "+getName());
		System.out.println("The account number is:- "+getAccnum());
		System.out.println("The balance amount is:- "+getBalance());
		System.out.println("The location of the user is:- "+getlocation());
		
		System.out.println();
		System.out.println();
		}
	
	
	/*public String toString() {
		return "The user name is:- "+getName()+
		"\nThe account number is:- "+getAccnum()+
		"\n The balance amount is:- "+getBalance());
		System.out.println("The account number is:- "+Admin.acc[i].getAccnum());
		System.out.println("The location of the user is:- "+Admin.acc[i].getlocation());
	}*/
	}

