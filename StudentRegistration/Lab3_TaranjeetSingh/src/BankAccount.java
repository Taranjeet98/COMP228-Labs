public class BankAccount {
    private String accountNumber;
    private String name;
    private double balance;

    public BankAccount(String accountNumber, String name, double balance) 
    {
        if (!accountNumber.matches("\\d{9}")) {
            throw new IllegalArgumentException("Account number must be 9 digits");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amount) 
    {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be greater than 0");
        }
        balance = balance + amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException 
    {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be greater than 0");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance = balance - amount;
    }

    public void display() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.printf("Balance: %.2f%n", balance);
    }
}