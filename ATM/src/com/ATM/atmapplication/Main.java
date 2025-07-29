package com.ATM.atmapplication;
import java.util.*;
public class Main {
	Scanner sc = new Scanner(System.in);
	Admin admin = new Admin();
	User u=new User();
	Accounts ac = new Accounts();
	
	//Method for userLogin--checking whether the account number exists or not
	
	public void userLogin() {
		System.out.print(Colours.USER + "Enter the account number: " + Colours.RESET);
		String accountnum = sc.next();
		boolean found=u.login(accountnum);
		if(found) {
			System.out.print(Colours.USER + "Please enter your pin: " + Colours.RESET);
			int pinbyuser = sc.nextInt();
			boolean verified = u.pinverification(accountnum,pinbyuser);
			if(verified) {
				boolean flag = true;
				while(flag) {
					System.out.println(Colours.USER + "\nWhich operation do you want to perform:" + Colours.RESET);
					System.out.println(Colours.DEPOSIT + "1. Deposit money" + Colours.RESET);
					System.out.println(Colours.WITHDRAW + "2. Withdraw money" + Colours.RESET);
					System.out.println(Colours.CHECK_BALANCE + "3. Check balance" + Colours.RESET);
					System.out.println(Colours.LOGGING_OFF + "4. Exit the account" + Colours.RESET);
					int option = sc.nextInt();
						switch(option) {
						case 1:
							System.out.print(Colours.DEPOSIT + "Enter the amount to deposit: " + Colours.RESET);
							int money = Math.abs(sc.nextInt());
							if(money>=100) {
								u.deposit(money,accountnum);
								break;
							}
							else {
								System.out.println(Colours.WITHDRAW + "Deposit must be at least ₹100" + Colours.RESET);
								money = sc.nextInt();
							}
							break;
						case 2:
							System.out.print(Colours.WITHDRAW + "Enter the amount to withdraw: " + Colours.RESET);
							int withdrawamt = sc.nextInt();
							u.withdraw(withdrawamt,accountnum);
							break;
						case 3:
							System.out.print(Colours.CHECK_BALANCE + "Your balance is: " + Colours.RESET);
							u.checkBalance(accountnum);
							break;
						case 4:
							System.out.println("Exiting the system");
							flag = false;
						}
					}
			}
			else {
				System.out.println(Colours.WITHDRAW + "Your pin is wrong." + Colours.RESET);
		}
		}else {
			System.out.println(Colours.WITHDRAW + "Your account doesn't exist." + Colours.RESET);
		}
	}
	public void adminLogin() {
		System.out.print(Colours.ADMIN + "Please enter your Admin pin number: " + Colours.RESET);
		int pinnumber = sc.nextInt();
		boolean flag = true;
		while(flag) {
			if(pinnumber == admin.pin) {
				System.out.println(Colours.ADMIN + "\nWhich operation do you want to perform:" + Colours.RESET);
				System.out.println(Colours.CREATE_ACC + "1. Account creation" + Colours.RESET);
				System.out.println(Colours.DISPLAY_ACC + "2. Display accounts" + Colours.RESET);
				System.out.println(Colours.DELETE_ACC + "3. Delete an account" + Colours.RESET);
				System.out.println(Colours.LOGGING_OFF + "4. Logging off" + Colours.RESET);
				int options = sc.nextInt();
				
				switch(options) {
				case 1:
					System.out.println(Colours.CREATE_ACC + "\n=== Account Creation ===" + Colours.RESET);
					admin.createAcc();
					break;
				case 2:
					System.out.println(Colours.DISPLAY_ACC + "\n=== Displaying Account Details ===" + Colours.RESET);
					admin.displayAccounts();
					break;
				case 3:
					if(Admin.count==0) {
						System.out.println(Colours.DELETE_ACC + "There are no accounts to delete." + Colours.RESET);
					}
					else {
						System.out.print(Colours.DELETE_ACC + "Enter account number to delete: " + Colours.RESET);
					String accnum = sc.next();
					admin.deleteAccounts(accnum);
					}
					break;
				case 4:
					System.out.println(Colours.LOGGING_OFF + "Logging out of admin account..." + Colours.RESET);
					flag = false;
					break;
					}
			}
			else {
				flag = false;
				System.out.println(Colours.DELETE_ACC + "Your admin pin is wrong!" + Colours.RESET);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Main m=new Main();
		boolean flag = true;
		while(flag) {
			System.out.println("\n" + Colours.ADMIN + "1. Admin login" + Colours.RESET);
			System.out.println(Colours.USER + "2. User login" + Colours.RESET);
			System.out.println(Colours.LOGGING_OFF + "3. Log off the system" + Colours.RESET);
			int options = sc.nextInt();
			switch(options) {
			case 1:
			    m.adminLogin();
				break;
			case 2:
				if(Admin.count>=1) {
					m.userLogin();
					break;
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