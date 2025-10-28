import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;

    public Customer(String customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void displayInfo() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    // Getters (encapsulation best practice)
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
}


class BankAccount {
    private String accountNumber;
    private Customer owner;
    private double balance;

    public BankAccount(String accountNumber, Customer owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        if (balance < 0) {
            System.out.println("Initial balance cannot be negative. Setting to $0.");
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Deposited $" + amount + " successfully.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal canceled.");
        } else {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " successfully.");
        }
    }

    public void displayBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    public void displayAccountInfo() {
        System.out.println("\n===== Account Summary =====");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("----- Owner Information -----");
        owner.displayInfo();
        System.out.println("=============================\n");
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String getAccountNumber() { return accountNumber; }
    public Customer getOwner() { return owner; }
    public double getBalance() { return balance; }

    
}

class Transaction
{
  private String transactionId;
  private String type;
  private double  amount;
  private LocalDate timeStamp;
  private BankAccount account;

    public Transaction(String transactionId, String type, double amount, BankAccount account) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.timeStamp = LocalDate.now();
        this.account = account;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

  public void printTransactionDetails() {
        System.out.println("===== Transaction Details =====");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Type: " + type);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Timestamp: " + timeStamp);
        System.out.println("===============================");
    }

  
}

class SavingsAccount extends BankAccount
{
  private double interestRate;

    public SavingsAccount(double interestRate, String accountNumber, Customer owner, double balance) {
        super(accountNumber, owner, balance);
        this.interestRate = interestRate;
      
    }

    @Override
    public void withdraw(double amount) {
      double balance = super.getBalance();
      if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal canceled.");
        } else if(balance-amount>=500) {
            balance -= amount;
            super.setBalance(balance);
            System.out.println("Withdrew $" + amount + " successfully.");
        }else{
          System.out.println("Cannot withdraw below minimum balance");
        }
        
    }

    

    public void applyAnnualInterest()
    {
      super.setBalance(super.getBalance()+(super.getBalance()*(0.01*interestRate))); 
    }

    
}



class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(double overdraftLimit, String accountNumber, Customer owner, double balance) {
        super(accountNumber, owner, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
      double balance = super.getBalance();
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance+overdraftLimit) {
            System.out.println("Insufficient funds. Withdrawal canceled.");
        } else {
            balance -= amount;
            super.setBalance(balance);
            System.out.println("Withdrew $" + amount + " successfully.");
        }
    }
    
}








class Bank{
  private ArrayList<Customer> customers;
  private ArrayList<BankAccount> accounts;
  private ArrayList<Transaction> transactions;
  Scanner sc1 = new Scanner(System.in);
    public Bank() {
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

  
  

  private String name;
  private String email;
  private String phone;
  private String customerId;

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
    

  public void createCustomer()
  {
  
    System.out.println("\nCreating a new Customer ");
    System.out.print("Enter Customer Name: ");
    name = sc1.nextLine();
    System.out.print("Enter email: ");
    email = sc1.nextLine();
    System.out.print("Enter Customer Phone: ");
    phone = sc1.nextLine();

    if(customers.isEmpty())
    {
      customerId =name+"000"+1;
    }
    else{
      System.out.println("Profile Created for "+name);
      customerId =name+"000"+(customers.size()+1);
    }

    
    System.out.print("\nCustomer Created with ID: "+customerId);

    Customer customer = new Customer(customerId, name, email, phone);
    customers.add(customer);
  }





  public void openAccount(Customer customer)
  {

    System.out.print("\nSelect Account Type: \n1. Savings Account\n2. Current Account\nEnter Choice: ");
    int choice = sc1.nextInt();
    switch (choice) {
      
      case 1 -> {
          String accNumber = customer.getCustomerId()+"_SA_";
          SavingsAccount sa = new SavingsAccount(3.0, accNumber, customer, 2000);
          System.out.println("Savings Account created Successfully");
          accounts.add(sa);
          displayAccountInfo();
          }

      case 2 -> {
          CurrentAccount ca = new CurrentAccount(1000, customer.getCustomerId()+"_CA_", customer, 5000);
          System.out.println("Current Account created Successfully");
          accounts.add(ca);
          displayAccountInfo();
          }

      default -> System.out.println("WRONG OPTION");
                
    }
  }
          



  private void displayAccountInfo() {
    
  }




  public BankAccount findAccountByAccNumber(String accNo)
  {
    BankAccount found = accounts.stream()
                            .filter(account -> accNo.equals(account.getAccountNumber()))
                            .findFirst()
                            .orElse(null);
    return found;
  }





  public Customer findAccountByCustomerID(String customerId)
  {
    System.out.println("\nCustomer Id verified");
    Customer c1= customers.stream().filter(customer -> customerId.equals(customer.getCustomerId())).findFirst().orElse(null);
    if(c1 == null)
      System.out.println("Invalid Customer");
    return c1;
  }





 public void performDeposit(String accNo, double amount)
 {
  BankAccount account = findAccountByAccNumber(accNo);
  if (account == null) {
        System.out.println("X Account not found for Account Number: " + accNo);
        return;
  }
  if (amount <= 0) {
        System.out.println("X Invalid deposit amount. Must be greater than 0.");
        return;
    }

    // Perform the deposit
    account.deposit(amount);
    recordTransaction("Deposit", amount, account);
    System.out.println("* Successfully deposited into Account " + accNo );
    account.displayBalance();

 }





 public void performWithdrawal(String accNo, double amount)
 {
  BankAccount account = findAccountByAccNumber(accNo);
  if (account == null) {
        System.out.println("X Account not found for Account Number: " + accNo);
        return;
  }

  if (amount <= 0) {
        System.out.println("X Invalid Withdrawal amount. Must be greater than 0.");
        return;
    }
  account.withdraw(amount);
  recordTransaction("Withdraw", amount, account);
  account.displayBalance();
}

public void transferFunds(String fromAcc, String toAcc, double amount)
{
  BankAccount fromAccount = findAccountByAccNumber(fromAcc);
  BankAccount toAccount = findAccountByAccNumber(toAcc);
  if (fromAccount == null || toAccount == null ) {
        System.out.println("X Account not found for Account Number: ");
        return;
  }
  
  if(fromAccount.getBalance() - amount > 0)
  {
    fromAccount.withdraw(amount);
    recordTransaction("Withdraw", amount, fromAccount);
    toAccount.deposit(amount);
    recordTransaction("Deposit", amount, toAccount);
  }else{
    System.out.println("Insufficient amount to be transerred");
  }
}

public void displayAccount()
{
  accounts.forEach(i ->i.displayAccountInfo());
}

  public void displayAllTransactons()
  {
    
  }

  protected void recordTransaction(String type, double amount, BankAccount account)
  {

    Transaction transaction = new Transaction(transactions.size()+1+"", type, amount,account);
    transactions.add(transaction);
  }

}
//






public class MiniBankingSystem {
  private static int choice;

  public static void main(String[] args) {
    System.out.println("\n Starting application....\n");
    Bank myBank = new Bank();
    Scanner sc = new Scanner(System.in);
    
do {
    System.out.println("""
                       \n=== Welcome to SMARTBANK SYSTEM === 
                       1. Create Customer
                       2. Open Account
                       3. Deposit
                       4. Withdraw
                       5. Transfer
                       6. Apply Interest
                       7. Display Account
                       8. Show Transaction
                       9. Exit
                       """);
    System.out.print("Please choose: ");

    int choice = 0;
    try {
        choice = sc.nextInt();
    } catch (InputMismatchException e) {
        System.out.println("Invalid input! Please enter a number.");
        sc.nextLine(); // clear invalid input
        continue; // restart loop
    }
    sc.nextLine();  // consume leftover newline

    switch (choice) {
        case 1 -> {
            myBank.createCustomer();
        }
        case 2 -> {
            System.out.print("\nEnter Customer ID: ");
            String id = sc.nextLine();
            Customer customer = myBank.findAccountByCustomerID(id);
            if (customer == null) {
                System.out.println("Customer not found");
                break;
            }
            myBank.openAccount(customer);
        }
        case 3 -> {
            System.out.print("\nEnter Customer ID: ");
            String id = sc.nextLine();
            Customer customer = myBank.findAccountByCustomerID(id);
            if (customer == null) {
                System.out.println("Customer not found");
                break;
            }
            System.out.println("Enter the amount to be deposited: ");
            int amount = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Account Number: (add an _SA_)");
            String accID = sc.nextLine();
            myBank.performDeposit(accID, amount);
        }
        case 4 -> {
            System.out.print("\nEnter Customer ID: ");
            String id = sc.nextLine();
            Customer customer = myBank.findAccountByCustomerID(id);
            if (customer == null) {
                System.out.println("Customer not found");
                break;
            }
            System.out.println("Enter the amount to be Withdrawn: ");
            int amount = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Account Number: (add an _SA_)");
            String accID = sc.nextLine();
            myBank.performWithdrawal(accID, amount);
        }
        case 5 -> {
            System.out.print("Enter the from Account: ");
            String fromAcc = sc.nextLine();
            System.out.print("Enter the Receivers Account: ");
            String toAcc = sc.nextLine();
            System.out.print("Enter the amount to transfer: ");
            int amount = sc.nextInt();
            sc.nextLine();
            myBank.transferFunds(fromAcc, toAcc, amount);
        }
        case 6 -> {
            ArrayList<BankAccount> accounts = myBank.getAccounts();
            accounts.forEach(acc -> {
                if (acc instanceof SavingsAccount) {
                    ((SavingsAccount) acc).applyAnnualInterest();
                }
            });
        }
        case 7 -> {
            ArrayList<BankAccount> accounts = myBank.getAccounts();
            accounts.forEach(acc -> {
                System.out.println("Displaying all the accounts");
                acc.displayAccountInfo();
            });
        }
        case 8 -> {
            // You can add display transactions here if implemented
        }
        case 9 -> {
            System.out.println("Exiting application...");
            return;
        }
        default -> {
            System.out.println("Invalid choice, please enter a number from 1 to 9.");
        }
    }
} while (choice <= 9);
  }
}
