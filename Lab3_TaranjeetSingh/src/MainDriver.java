import java.util.Scanner;

public class MainDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account1 = null;
        while (account1 == null) 
        {
            try {
                System.out.print("Enter Account Number: ");
                String accountNumber = sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Initial Balance: ");
                double balance = Double.parseDouble(sc.nextLine());
                account1 = new BankAccount(accountNumber, name, balance);
                System.out.println("Account created successfully.");
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Please enter a valid balance.");
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("Error: " + e.getMessage());
            } 
            finally 
            {
                System.out.println("Account creation processed.");
            }
        }

        BankAccount account2 = new BankAccount("123456789", "Zeel", 1000.00);
        BankAccount account3 = new BankAccount("987654321", "Ravi", 2000.00);
        System.out.println("\nAccount 1:");
        account1.display();
        while (true) 
        {
            try 
            {
                System.out.print("Enter your deposit amount: ");
                double deposit = Double.parseDouble(sc.nextLine());
                account1.deposit(deposit);
                break;
            }  
            catch (NumberFormatException e) 
            {
                System.out.println("Deposit amount invalid.");
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("Error: " + e.getMessage());
            } 
            finally {
                System.out.println("Deposit transaction is processed.");
            }
        }
        while (true) 
        {
            try 
            {
                System.out.print("Enter your withdrawal amount: ");
                double withdrawal = Double.parseDouble(sc.nextLine());
                account1.withdraw(withdrawal);
                break;
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid withdrawal amount.");
            } 
            catch (InsufficientFundsException e) 
            {
                System.out.println("Error: " + e.getMessage());
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("Error: " + e.getMessage());
            } 
            finally 
            {
                System.out.println("Withdrawal transaction is processed.");
            }
        }

        System.out.println("\nUpdated Account 1:");
        account1.display();

        try {
            account2.deposit(500);
            account2.withdraw(200);
        }
        catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());

        } 
        finally {
            System.out.println("Account 2 transaction processed.");
        }
        System.out.println("\nAccount 2:");
        account2.display();

        try {
            account3.deposit(500);
            account3.withdraw(300);
        } 
        catch (InsufficientFundsException e) 
        {
            System.out.println("Error: " + e.getMessage());
        } 
        finally {
            System.out.println("Account 3 transaction processed.");
        }

        System.out.println("\nAccount 3:");
        account3.display();
        System.out.println("\nAll the transactions completed.");
        sc.close();
    }
}