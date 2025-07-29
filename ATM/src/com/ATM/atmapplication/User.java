package com.ATM.atmapplication;
public class User {
	public boolean login(String accountnum) {
		for(int i=0;i<Admin.count;i++) {
			if(Admin.acc[i].getAccnum().equalsIgnoreCase(accountnum)){
				System.out.println(Colours.USER + "🔓 Login successful!" + Colours.RESET);
				return true;
			}
		}
		System.out.println(Colours.USER + "❌ Login failed! Account not found." + Colours.RESET);
		return false;
	}
	public boolean pinverification(String accountnum,int pin) {
		for(int i=0;i<Admin.count;i++) {
			if(Admin.acc[i].getAccnum().equalsIgnoreCase(accountnum) && Admin.acc[i].getPin() == pin) {
				System.out.println(Colours.USER + "✅ PIN verified successfully!" + Colours.RESET);
				return true;
			}
		}
		System.out.println(Colours.USER + "⚠️ Incorrect PIN!" + Colours.RESET);
		return false;
	}
	public void deposit(double amt,String accountnum) {
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
					break;
				}
				else {
					System.out.println(Colours.DEPOSIT + "Please enter a positive amount to deposit." + Colours.RESET);
					return;
				}
			}
		}
		if(!found) {
			System.out.println(Colours.DEPOSIT + "❌ Account doesn't exist." + Colours.RESET);
		}
	}
	public void withdraw(double amt,String accountnum) {
		double temp = Math.abs(amt);
		for(int i=0;i<Admin.count;i++) {
			if(Admin.acc[i].getAccnum().equalsIgnoreCase(accountnum)) {
				System.out.println(Colours.WITHDRAW + "💸 Attempting to withdraw ₹" + temp + Colours.RESET);
					Admin.acc[i].withdraw(temp);
					break;
			}
		}
	}
	public void checkBalance(String amountnum) {
		for(int i=0;i<Admin.count;i++) {
			if(Admin.acc[i].getAccnum().equalsIgnoreCase(amountnum)) {
				System.out.println(Colours.CHECK_BALANCE + "💼 Current balance: ₹" + Admin.acc[i].getBalance() + Colours.RESET);
			}
		}
	}
}