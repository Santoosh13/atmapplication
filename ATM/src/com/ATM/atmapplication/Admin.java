package com.ATM.atmapplication;
import java.util.*;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;

import com.ATM.atmapplication.exceptions.*;
public class Admin {
	
	int pin = 1308;
	//step:-1 creating an array of account number's objects
	
	static Accounts []acc = new Accounts[100];
	Scanner sc = new Scanner(System.in);
	static int count=0;
	static String numberforacc = "sbi00";
	static int a=1;
	//method for creating account objects
	
	public void createAcc() {
		
		System.out.println(Colours.CREATE_ACC + "\n=== Account Creation ===" + Colours.RESET);
		
		System.out.println("Enter your name:-");
		String name=sc.next();
		
		System.out.println("Enter your location:-");
		String loc = sc.next();
		
		String accnum=	numberforacc+a;
		a++;
		System.out.println("Your account number is: " + Colours.CREATE_ACC + accnum + Colours.RESET);
		
		System.out.println("Please set your Pin Number:-");
		int pinnum = sc.nextInt();
		
		System.out.println("Enter your amount:-");
		double bal = sc.nextInt();
		
		Accounts ac = new Accounts(name,loc,accnum,pinnum,bal);
		acc[count++]=ac;
		
		saveAccountsToFile();
		
		System.out.println(Colours.CREATE_ACC + "✅ Your account has been successfully created!" + Colours.RESET);
		
	}
	public static void saveAccountsToFile() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accountsFile.ser")) ){
			
			oos.writeObject(acc);
			oos.writeInt(count);
			oos.writeInt(a);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fetchAccountsfromfile() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accountsFile.ser"))){
			
			acc = (Accounts[])ois.readObject();
			count = ois.readInt();
			a = ois.readInt();
		}
		catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void displayAccounts() throws NoAccountsFoundException {
		fetchAccountsfromfile();
		System.out.println(Colours.DISPLAY_ACC + "\n=== Display of all accounts ==="+ Colours.RESET);
		
		if(count == 0) {
			throw new NoAccountsFoundException("There are no accounts to display");
		}
		
		for(int i=0;i<count;i++) {
			System.out.println("The details of the user are:- ");
			acc[i].details();
		}
	}
	
	public void deleteAccounts(String accountnumber) throws AccountnotFoundException {
		String accounttodelete = accountnumber;
		boolean found = false;
		for(int i=0;i<count;i++) {
			if(accounttodelete.equalsIgnoreCase(acc[i].getAccnum())) {
				acc[i]= acc[count-1];
				acc[count-1]=null;
				count--;
				found = true;
				break;
			}
		}
		if(found) {
			saveAccountsToFile();
			System.out.println(Colours.DELETE_ACC + "🗑️ Account number " + accountnumber + " is deleted." + Colours.RESET);
		}
		else {
			throw new AccountnotFoundException(Colours.DELETE_ACC + "⚠️ Account not found!" + Colours.RESET);
		}
		
	}
	public static void main(String[] args) throws NoAccountsFoundException,AccountnotFoundException {
	
		Admin admin = new Admin();
		admin.fetchAccountsfromfile();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("\n" + Colours.ADMIN +
				    "Enter your option:\n1. Create Account\n2. Display Accounts\n3. Delete Account\n4. Exit" 
				    + Colours.RESET);
			int option = sc.nextInt();
			
			switch(option)
			{
			case 1:
				admin.createAcc();
				break;
			case 2:
				try {
					admin.displayAccounts();
				}
				catch(NoAccountsFoundException e) {
					System.out.println(Colours.DISPLAY_ACC+e.getMessage()+Colours.RESET);
				}
				break;
			case 3:
				System.out.print("Enter account number to delete: ");
				String accountnumber = sc.next();
				try {
					admin.deleteAccounts(accountnumber);
				}
				catch(AccountnotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 4: {
                System.out.println("Logging out...");
                return;
            }
            default: {
            	System.out.println("Invalid option!");
            	}
			}
		}
	}
}
