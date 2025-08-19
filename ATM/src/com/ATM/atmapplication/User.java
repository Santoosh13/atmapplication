package com.ATM.atmapplication;

import com.ATM.atmapplication.exceptions.AccountnotFoundException;

public class User {
	
	public boolean login(String accountnum){
		for(int i=0;i<Admin.count;i++) {
			if(Admin.acc[i].getAccnum().equalsIgnoreCase(accountnum)){
				System.out.println(Colours.USER + "🔓 Login successful!" + Colours.RESET);
				return true;
			}
		}
		return false;	
	}
	
	public boolean pinverification(String accountnum,int pin) {
		for(int i=0;i<Admin.count;i++) {
			if(Admin.acc[i].getAccnum().equalsIgnoreCase(accountnum) && Admin.acc[i].getPin() == pin) {
				System.out.println(Colours.USER + "✅ PIN verified successfully!" + Colours.RESET);
				return true;
			}
		}
		return false;
	}
	public void deposit(double amt,String accountnum) throws AccountnotFoundException {
		amt = Math.abs(amt);
		boolean found = false;
		for(int i=0;i<Admin.count;i++) {
			if(Admin.acc[i].getAccnum().equalsIgnoreCase(accountnum)) {
				if(amt>0) {
					double current = Admin.acc[i].getBalance();
					Admin.acc[i].setBalance(current+amt);
					System.out.println(Colours.DEPOSIT + "💰 Amount deposited successfully!" + Colours.RESET);
					System.out.println(Colours.DEPOSIT + "Now your balance is: ₹" + Admin.acc[i].getBalance() + Colours.RESET);
					found = true;
					Admin.saveAccountsToFile();
					break;
				}
				else {
					System.out.println(Colours.DEPOSIT + "Please enter a positive amount to deposit." + Colours.RESET);
					return;
				}
			}
		}
		if(!found) {
			//System.out.println(Colours.DEPOSIT + "❌ Account doesn't exist." + Colours.RESET);
			throw new AccountnotFoundException("Account doesn't exist");
		}
	}
	public void withdraw(double amt,String accountnum) throws AccountnotFoundException{
		double temp = Math.abs(amt);
		boolean found = false;
		for(int i=0;i<Admin.count;i++) {
			if(Admin.acc[i].getAccnum().equalsIgnoreCase(accountnum)) {
				found = true;
				if(Admin.acc[i].getBalance()>=temp) {
					Admin.acc[i].setBalance(Admin.acc[i].getBalance()-temp);
					System.out.println(Colours.WITHDRAW + "✅ Withdrawal successful! New balance: ₹" + Admin.acc[i].getBalance() + Colours.RESET);
					Admin.saveAccountsToFile();
				}
				else {
					System.out.println(Colours.WITHDRAW + "❌ Insufficient balance!" + Colours.RESET);
				}
				break;
			}
		}
		if(!found) {
			throw new AccountnotFoundException("This account number doesn't exist.");
		}
	}
	public void checkBalance(String accountnum) throws AccountnotFoundException{
		boolean found = false;
		for(int i=0;i<Admin.count;i++) {
			if(Admin.acc[i].getAccnum().equalsIgnoreCase(accountnum)) {
				found = true;
				System.out.println(Colours.CHECK_BALANCE + "💼 Current balance: ₹" + Admin.acc[i].getBalance() + Colours.RESET);
				break;
			}
		}
		if(!found) {
			throw new AccountnotFoundException("This account number doesn't exists.");
		}
	}
}