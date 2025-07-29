package com.ATM.atmapplication;

public class Colours {
    // ANSI color codes
	// Reset
	public static final String RESET = "\u001B[0m";

	// Admin Main and Operations
	public static final String ADMIN = "\u001B[36m";      // Cyan
	public static final String CREATE_ACC = "\u001B[96m"; // Bright Cyan
	public static final String DISPLAY_ACC = "\u001B[94m"; // Light Blue
	public static final String DELETE_ACC = "\u001B[95m"; // Magenta

	// User Main and Operations
	public static final String USER = "\u001B[35m";        // Purple
	public static final String DEPOSIT = "\u001B[92m";     // Light Green
	public static final String WITHDRAW = "\u001B[91m";    // Light Red
	public static final String CHECK_BALANCE = "\u001B[93m"; // Light Yellow

	// System Logoff
	public static final String LOGGING_OFF = "\u001B[90m"; // Dark Gray


    public static void main(String[] args) {
    	System.out.println(ADMIN + "Welcome to Admin Panel" + RESET);
    	System.out.println(CREATE_ACC + "Account Created Successfully" + RESET);
    	System.out.println(USER + "Welcome User!" + RESET);
    	System.out.println(WITHDRAW + "Amount withdrawn: ₹500" + RESET);
    	System.out.println(LOGGING_OFF + "Logging off the system..." + RESET);

    }
}


