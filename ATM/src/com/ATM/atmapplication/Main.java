package com.ATM.atmapplication;

import java.util.*;

import com.ATM.atmapplication.exceptions.*;

public class Main {
	Scanner sc = new Scanner(System.in);
	Admin admin = new Admin();
	User u = new User();
	Accounts ac = new Accounts();

	// Method for userLogin--checking whether the account number exists or not

	public void userLogin() throws AccountnotFoundException, InvalidPinException, InsufficentFundsException {
		System.out.print(Colours.USER + "Enter the account number: " + Colours.RESET);

		String accountnum = sc.next();
		boolean found = u.login(accountnum);

		if (found) {

			System.out.print(Colours.USER + "Please enter your pin: " + Colours.RESET);

			int pinbyuser = sc.nextInt();
			boolean verified = u.pinverification(accountnum, pinbyuser);
			
			if (verified) {
				
				boolean flag = true;
				while (flag) {

					System.out.println(Colours.USER + "\nWhich operation do you want to perform:" + Colours.RESET);
					System.out.println(Colours.DEPOSIT + "1. Deposit money" + Colours.RESET);
					System.out.println(Colours.WITHDRAW + "2. Withdraw money" + Colours.RESET);
					System.out.println(Colours.CHECK_BALANCE + "3. Check balance" + Colours.RESET);
					System.out.println(Colours.LOGGING_OFF + "4. Exit the account" + Colours.RESET);

					int option = sc.nextInt();
					switch (option) {
					
					case 1:
						System.out.print(Colours.DEPOSIT + "Enter the amount to deposit: " + Colours.RESET);
						int money = Math.abs(sc.nextInt());
						
						do {
							System.out.print(Colours.DEPOSIT + "Enter the amount to deposit (min ₹100): " + Colours.RESET);
						    money = sc.nextInt();
						}
						while(money<100);
						u.deposit(money, accountnum);
						break;
					case 2:
						System.out.print(Colours.WITHDRAW + "Enter the amount to withdraw: " + Colours.RESET);
						int withdrawamt = sc.nextInt();
						u.withdraw(withdrawamt, accountnum);
						break;
					case 3:
						u.checkBalance(accountnum);
						break;
					case 4:
						System.out.println("Exiting the system");
						flag = false;
					}
				}
			} else {
				System.out.println(Colours.USER + "❌ Invalid Pin!" + Colours.RESET);
			    return;
			}
		} else {
		    System.out.println(Colours.USER + "❌ Your account was not found." + Colours.RESET);
		    return;
		}
	}

	public void adminLogin() throws NoAccountsFoundException, InvalidPinException {
		System.out.print(Colours.ADMIN + "Please enter your Admin pin number: " + Colours.RESET);
		int pinnumber = sc.nextInt();
		boolean flag = true;
		while (flag) {
			if (pinnumber == admin.pin) {
				System.out.println(Colours.ADMIN + "\nWhich operation do you want to perform:" + Colours.RESET);
				System.out.println(Colours.CREATE_ACC + "1. Account creation" + Colours.RESET);
				System.out.println(Colours.DISPLAY_ACC + "2. Display accounts" + Colours.RESET);
				System.out.println(Colours.DELETE_ACC + "3. Delete an account" + Colours.RESET);
				System.out.println(Colours.LOGGING_OFF + "4. Logging off" + Colours.RESET);
				int options = sc.nextInt();

				switch (options) {
				case 1:
					System.out.println(Colours.CREATE_ACC + "\n=== Account Creation ===" + Colours.RESET);
					admin.createAcc();
					break;
				case 2:
					System.out.println(Colours.DISPLAY_ACC + "\n=== Displaying Account Details ===" + Colours.RESET);
					admin.displayAccounts();
					break;
				case 3:
					if (Admin.count == 0) {
						System.out.println(Colours.DELETE_ACC + "There are no accounts to delete." + Colours.RESET);
					} else {
						System.out.print(Colours.DELETE_ACC + "Enter account number to delete: " + Colours.RESET);
						String accnum = sc.next();
						try {
							admin.deleteAccounts(accnum);
						} catch (AccountnotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				case 4:
					System.out.println(Colours.LOGGING_OFF + "Logging out of admin account..." + Colours.RESET);
					flag = false;
					break;
				}
			} else {
				flag = false;
				System.out.println(Colours.DELETE_ACC + "Your admin pin is wrong!" +Colours.RESET);
			}
		}
	}

	public static void main(String[] args) throws AccountnotFoundException, InvalidPinException, InvalidAmountException,
			InsufficentFundsException, NoAccountsFoundException {
		Scanner sc = new Scanner(System.in);

		Main m = new Main();
		Admin.fetchAccountsfromfile();
		boolean flag = true;
		while (flag) {
			System.out.println("\n" + Colours.ADMIN + "1. Admin login" + Colours.RESET);
			System.out.println(Colours.USER + "2. User login" + Colours.RESET);
			System.out.println(Colours.LOGGING_OFF + "3. Log off the system" + Colours.RESET);
			int options = sc.nextInt();
			switch (options) {
			case 1:
				try {
					m.adminLogin();
				}
				catch(InvalidPinException | NoAccountsFoundException e) {
					System.out.println(Colours.ADMIN + "❌ " + e.getMessage() + Colours.RESET);
				}
				break;
			case 2:
				if(Admin.count>=1) {
					try {
						m.userLogin();
					}
					catch(AccountnotFoundException | InvalidPinException | InsufficentFundsException e) {
						System.out.println(Colours.USER + "❌ " + e.getMessage() + Colours.RESET);
					}
				}
				else {
					System.out.println(Colours.WITHDRAW + "There are no user accounts." + Colours.RESET);
				}
				
				break;
			case 3:
				flag = false;
				System.out.println(Colours.LOGGING_OFF + "System is logged off..." + Colours.RESET);
				break;
			}
		}
	}
}