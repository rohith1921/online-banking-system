package com.bank.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.bank.model.AccountType;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.service.AccountService;
import com.bank.service.AdminService;
import com.bank.service.TransactionService;
import com.bank.service.UserService;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.AdminServiceImpl;
import com.bank.service.impl.TransactionServiceImpl;
import com.bank.service.impl.UserServiceImpl;

public class MainApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		UserService userService = new UserServiceImpl();
		AccountService accountService = new AccountServiceImpl();
		TransactionService transactionService = new TransactionServiceImpl();
		AdminService adminService = new AdminServiceImpl();
		
		User loggedInUser = null;
		
		while(true) {
			System.out.println("\n========== ONLINE BANKING SYSTEM ===============");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Admin Login");
			System.out.println("4. Exit");
			System.out.println("choose option: ");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			try {
				switch(choice) {
				case 1:
					User user = new User();
					
					System.out.print("Name: ");
					user.setName(sc.nextLine());
					
					System.out.print("Email: ");
                    user.setEmail(sc.nextLine());

                    System.out.print("Mobile: ");
                    user.setMobilenumber(sc.nextLong());
                    sc.nextLine();

                    System.out.print("Password: ");
                    user.setPassword(sc.nextLine());

                    userService.registerUser(user); 
                    System.out.println("User registered successfully!");
                    break;
                    
				case 2:
					System.out.print("Email: "); 
					String email = sc.nextLine();
					
					System.out.print("Password: ");
					String password = sc.nextLine();
					
					loggedInUser = userService.login(email, password);
					
					if(loggedInUser !=null) {
						System.out.println("Login Successful!");
						userMenu(sc, loggedInUser, accountService, transactionService);
					} else {
						System.out.println("Invalid credentials");
					}
					break;
					
				case 3:
					System.out.print("Admin Email: ");
					String AdminEmail = sc.nextLine();
					
					System.out.print("Enter Admin Password: ");
					String AdminPassword = sc.nextLine();
					
					if(adminService.adminLogin(AdminEmail, AdminPassword)) {
						System.out.println("Login Successful!");
						adminMenu(sc, adminService); 
					} else {
						System.out.println("Invalid credentials");
					}
					break;
					
				case 4:
					System.out.println("Thank you for using Online Banking!");
					sc.close();
					System.exit(0);
					
				default:
					System.out.println("Invalid choice!");
				}
			} catch(Exception e) {
//				e.getStackTrace();
				System.out.println("ERROR: "+e.getMessage());
			}
		}
	}
	
	private static void userMenu(Scanner sc, User user, AccountService accountService, TransactionService transactionService) {
		while(true) {
			System.out.println("\n===== BANKING MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance"); 
            System.out.println("6. Mini Statement");
            System.out.println("7. Statement");
            System.out.println("8. Statement By Date");
            System.out.println("9. Change PIN");
            System.out.println("10. Logout"); 
            System.out.print("Choose option: ");

            int option = sc.nextInt();
            
            try {
            		switch (option) {
					case 1:
						System.out.println("Choose Account Type:");
						System.out.println("1. Savings");
						System.out.println("2. Salary");
						System.out.println("3. Current");
						System.out.println("4. Fixed Deposit");
						System.out.println("5. Recurring Deposit");
						
						int typeChoice = sc.nextInt();
						
						AccountType accountType;
						switch(typeChoice) {
						case 1:
							accountType = AccountType.SAVINGS;
							break;
						case 2:
							accountType = AccountType.SALARY;
							break;
						case 3:
							accountType = AccountType.CURRENT;
							break;
						case 4:
							accountType = AccountType.FIXED;
							break;
						case 5:
							accountType = AccountType.RD;
							break;
						default:
							System.out.println("Invalid account type");
							return; 
						}
						
						System.out.print("Enter PIN: ");
						int pin = sc.nextInt();
						
						System.out.print("Initial Deposit: ");
						double initAmt = sc.nextDouble();
						
						accountService.createAccount(user, accountType, pin, initAmt);
						System.out.println("Account created successfully!");
						break;
						
					case 2:
						System.out.print("Account Number: ");
                        long accNo = sc.nextLong();

                        System.out.print("Amount: ");
                        double depAmt = sc.nextDouble();

                        transactionService.deposit(accNo, depAmt);
                        System.out.println("Deposit successful!");
                        break;
                        
					case 3:
						System.out.print("Account Number: ");
                        accNo = sc.nextLong();

                        System.out.print("Amount: ");
                        double withAmt = sc.nextDouble();

                        transactionService.withdraw(accNo, withAmt);
                        System.out.println("Withdrawal successful!");
                        break;
                        
					case 4:
						System.out.print("From Account: ");
                        long fromAcc = sc.nextLong();

                        System.out.print("To Account: ");
                        long toAcc = sc.nextLong();

                        System.out.print("Amount: ");
                        double transAmt = sc.nextDouble();

                        transactionService.transfer(fromAcc, toAcc, transAmt);
                        System.out.println("Transfer successful!");
                        break;
                        
					case 5:
						System.out.print("Account Number: ");
                        accNo = sc.nextLong();

                        double balance = accountService.getBalance(accNo);
                        System.out.println("Available Balance: " + balance);
                        break;
                        
					case 6:
						System.out.print("Account Number: ");
                        accNo = sc.nextLong();

                        List<Transaction> txns = transactionService.getMiniStatement(accNo);
                        if (txns == null || txns.isEmpty()) {
                            System.out.println("No transactions found.");
                        } else {
                            txns.forEach(System.out::println);
                        }
                     
                        break; 
                        
					case 7:
						System.out.print("Account Number: ");
						accNo = sc.nextLong();
						
						List<Transaction> txn = transactionService.getFullStatement(accNo);
						if (txn == null || txn.isEmpty()) {
						    System.out.println("No transactions found.");
						} else {
						    txn.forEach(System.out::println); 
						}
						break;
						
					case 8:
					    System.out.print("Account Number: ");
					    accNo = sc.nextLong();
					    sc.nextLine(); // consume newline

					    System.out.print("Enter Start Date (yyyy-MM-dd): ");
					    String startDateStr = sc.nextLine();

					    System.out.print("Enter End Date (yyyy-MM-dd): ");
					    String endDateStr = sc.nextLine();

					    LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
					    LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);

					    List<Transaction> dateRangeTxns =
					            transactionService.getStatementByDateRange(accNo, startDate, endDate);

					    if (dateRangeTxns == null || dateRangeTxns.isEmpty()) {
					        System.out.println("No transactions found in this date range.");
					    } else {
					        dateRangeTxns.forEach(System.out::println);
					    }
					    break;

                        
					case 9:
						System.out.print("Account Number: ");
                        accNo = sc.nextLong();

                        System.out.print("Old PIN: ");
                        int oldPin = sc.nextInt();

                        System.out.print("New PIN: ");
                        int newPin = sc.nextInt();

                        accountService.changePin(accNo, oldPin, newPin);
                        System.out.println("PIN changed successfully!");
                        break;
                        
					case 10:
						System.out.println("Logged out.");
                        return;

					default:
						System.out.println("Invalid option!");
					}
            } catch(Exception e) {
//            		e.getStackTrace(); 
            		System.out.println("ERROR: "+e.getMessage());
            }
		}
		
		
	}
	
	private static void adminMenu(Scanner sc, AdminService adminService) {
		while(true) {
			System.out.println("\n================ ADMIN PANEL ==================");
			System.out.println("1. View All Users");
	        System.out.println("2. View All Accounts");
	        System.out.println("3. View All Transactions");
	        System.out.println("4. Approve Accounts"); 
	     
	        System.out.println("5. Close Account");
	        System.out.println("6. Activate Account");
	        System.out.println("7. Logout");
	        System.out.print("Choose option: ");
	        
	        int choice = sc.nextInt();
	        
	        try {
	        	switch(choice) {
	        	case 1:
	        		adminService.getAllUsers().forEach(System.out::println);
	        		break;
	        		
	        	case 2:
	        		adminService.getAllAccounts().forEach(System.out::println);
	        		break;
	        		
	        	case 3:
	        		adminService.getAllTransactions().forEach(System.out::println);
	        		break;
	        		
	        	case 4:
	        	    System.out.print("Enter Account Number to approve: ");
	        	    long accNo = sc.nextLong();

	        	    try {
	        	        adminService.approveAccount(accNo);
	        	        System.out.println("Account approved successfully.");
	        	    } catch (Exception e) {
	        	        System.out.println("ERROR: " + e.getMessage());
	        	    }
	        	    break;

	        		
	        	case 5:
	        		System.out.println("Enter Account Number: ");
	        		adminService.closeAccount(sc.nextLong());
	        		System.out.println("Account closed");
	        		break;
	        		
	        	case 6:
	        		System.out.println("Enter Account Number: ");
	        		adminService.activateAccount(sc.nextLong());
	        		System.out.println("Account activated");
	        		break;
	        		
	        	case 7:
	        		System.out.println("Admin logged out");
	        		return;
	        		
	        	default:
	        		System.out.println("Invalid option");
	        	}
	        } catch(Exception e) {
	        	System.out.println("ERROR: "+e.getMessage());
	        }
		}
		
	}

}
