package com.ATM.atmapplication;
import java.util.*;
public class Admin {
	
	int pin = 1308;
	//step:-1 creating an array of account number's objects
	
	static Accounts []acc = new Accounts[100];
	Scanner sc = new Scanner(System.in);
	static int count=0;
	static String numberforacc = "sbi00";
	static int a=1;
	//method for creating account objects
	Accounts ac=new Accounts();
	
	public void createAcc() {
		
		System.out.println(Colours.CREATE_ACC + "\n=== Account Creation ===" + Colours.RESET);
		
		System.out.println("Enter your name:-");
		String name=sc.next();
		
		System.out.println("Enter your location:-");
		String loc = sc.next();
		
		/*System.out.println("Enter your accountnumber:-");
		boolean isUnique = false;
		String acno = "";
		while(!isUnique) {
			acno = sc.next();
			isUnique = true;
			for(int i=0;i<count;i++) {
				if(acno.equalsIgnoreCase(acc[i].getAccnum())) {
					System.out.println("Already an account exists");
					isUnique = false;
					break;
				}
			}
			
		}*/
		
		String accnum=	numberforacc+a;
		a++;
		System.out.println("Your account number is: " + Colours.CREATE_ACC + accnum + Colours.RESET);
		
		System.out.println("Please set your Pin Number:-");
		int pinnum = sc.nextInt();
		
		System.out.println("Enter your amount:-");
		double bal = sc.nextInt();
		
		Accounts ac = new Accounts(name,loc,accnum,pinnum,bal);
		System.out.println(Colours.CREATE_ACC + "✅ Your account has been successfully created!" + Colours.RESET);
		acc[count++]=ac;
	}
	public void displayAccounts() {
		System.out.println(Colours.DISPLAY_ACC + "\n=== Display of all accounts ==="+ Colours.RESET);
		
		if(count ==0) {
			System.out.println("There are no accounts to display");
		}
		for(int i=0;i<count;i++) {
			System.out.println("The details of the user are:- ");
			acc[i].details();
		}
	}
	
	public void deleteAccounts(String accountnumber) {
		String accounttodelete = accountnumber;
		boolean found = false;
		for(int i=0;i<count;i++) {
			if(accounttodelete.equalsIgnoreCase(acc[i].getAccnum())) {
				acc[i]= acc[count-1];
				acc[count-1]=null;
				count--;
				found = true;
			}
		}
		if(found) {
			System.out.println(Colours.DELETE_ACC + "🗑️ Account number " + accountnumber + " is deleted." + Colours.RESET);
		}
		else {
			System.out.println(Colours.DELETE_ACC + "⚠️ Account not found!" + Colours.RESET);
		}
		
	}
	public static void main(String[] args) {
		Admin admin = new Admin();
		Scanner sc = new Scanner(System.in);
		admin.createAcc();
		admin.displayAccounts();
		System.out.println("\n" + Colours.ADMIN + "Enter your option:\n1. Create Account\n2. Display Accounts\n3. Delete Account" + Colours.RESET);
		int option = sc.nextInt();
		
		switch(option)
		{
		case 1:
			admin.createAcc();
			break;
		case 2:
			admin.displayAccounts();
			break;
		case 3:
			String accountnumber = sc.next();
			admin.deleteAccounts(accountnumber);
			break;
		}
	}
}
