import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base Account Class
class Account {
    protected String accountHolder;
    protected double balance;
    protected List<String> transactionHistory;

    public Account(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + " | Balance: " + balance);
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + " | Balance: " + balance);
            System.out.println("Successfully withdrew: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void showBalance() {
        System.out.println("Current Balance: " + balance);
    }

    public void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }
}

// SavingsAccount inherits from Account
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountHolder, double initialBalance, double interestRate) {
        super(accountHolder, initialBalance);
        this.interestRate = interestRate;
    }

    // Method overriding example
    @Override
    public void withdraw(double amount) {
        if (balance - amount >= 500) { // Minimum balance rule
            super.withdraw(amount);
        } else {
            System.out.println("Withdrawal denied! Minimum balance of 500 required.");
        }
    }

    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        transactionHistory.add("Interest added: " + interest + " | Balance: " + balance);
        System.out.println("Interest added: " + interest);
    }
}

// Main class to run the simulation
public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create account
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        SavingsAccount account = new SavingsAccount(name, 1000, 5);

        int choice;
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Balance");
            System.out.println("4. Show Transaction History");
            System.out.println("5. Add Interest");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    account.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    account.withdraw(sc.nextDouble());
                    break;
                case 3:
                    account.showBalance();
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    account.addInterest();
                    break;
                case 6:
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);

        sc.close();
    }
}
