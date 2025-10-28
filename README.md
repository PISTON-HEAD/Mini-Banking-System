## 🏦 **SmartBank — A Mini Banking Simulation System**

### 🎯 **Objective**

Design and simulate a **small-scale digital banking system** that handles **customers, bank accounts, and transactions**, emphasizing **OOP principles, real-world logic, and data flow**.
You’ll model the inner working of a simplified bank — including account management, transaction tracking, and security checks — in a modular, extensible way.

---

## 🧩 **Level 1 — Core Structure (Basic Entities)**

At this level, focus on setting up the **core data model** with appropriate fields, constructors, and encapsulation.

### Classes to Implement

#### 1️⃣ `Customer`

* Fields:

  * `customerId` (String)
  * `name` (String)
  * `email` (String)
  * `phoneNumber` (String)
* Responsibilities:

  * Store basic customer information.
  * Provide `displayInfo()` method for summary.

#### 2️⃣ `BankAccount`

* Fields:

  * `accountNumber` (String)
  * `Customer owner` (object reference)
  * `double balance`
* Methods:

  * `deposit(double amount)`
  * `withdraw(double amount)`
  * `displayBalance()`
* Behavior:

  * Prevent overdrawing (no negative balance).
  * Display account and customer info neatly.
* Concept Focus: **Encapsulation and validation logic**

#### 3️⃣ `Transaction`

* Fields:

  * `String transactionId`
  * `String type` (Deposit / Withdrawal / Transfer)
  * `double amount`
  * `Date timestamp`
* Purpose:

  * Record and print transaction details for audit.

---

## ⚙️ **Level 2 — Account Specialization (Inheritance + Polymorphism)**

Expand functionality using **inheritance and method overriding**.

### Subclasses

#### 🔹 `SavingsAccount` extends `BankAccount`

* Field:

  * `double interestRate`
* Methods:

  * `applyAnnualInterest()` → adds interest to balance.
* Rule: A savings account cannot withdraw below a **minimum balance** (e.g., ₹500).

#### 🔹 `CurrentAccount` extends `BankAccount`

* Field:

  * `double overdraftLimit`
* Behavior:

  * Allows withdrawing beyond balance up to overdraft limit.
  * Charge penalty if overdraft exceeds limit.

#### 🔹 `LoanAccount` extends `BankAccount`

* Field:

  * `double loanBalance`
* Methods:

  * `makePayment(double amount)`
  * `calculateInterest(double rate, int months)`
* Concept: Demonstrates **hierarchical inheritance and behavior variation**.

---

## 💰 **Level 3 — Operations & System Logic**

Now you simulate **real-world banking actions and logic flows**.

### Create a new class:

#### 🏛️ `Bank`

* Fields:

  * `ArrayList<Customer> customers`
  * `ArrayList<BankAccount> accounts`
  * `ArrayList<Transaction> transactions`
* Methods:

  * `createCustomer()`
  * `openAccount(Customer customer, String type)`
  * `findAccountByNumber(String accNo)`
  * `performDeposit(String accNo, double amount)`
  * `performWithdrawal(String accNo, double amount)`
  * `transferFunds(String fromAcc, String toAcc, double amount)`
  * `displayAllAccounts()`
  * `displayAllTransactions()`

#### 🔒 Helper Method Examples:

* `protected boolean validateAmount(double amount)`
* `protected void recordTransaction(String type, double amount, BankAccount account)`

Focus: **Composition, encapsulation, and helper methods**.

---

## 🧠 **Level 4 — User Interaction & Simulation**

Turn your classes into a functioning **menu-driven console app**.

### In `SmartBankApp (main class)`, implement:

* Menu Options:

  1. Create new customer
  2. Open new account
  3. Deposit money
  4. Withdraw money
  5. Transfer money between accounts
  6. Apply annual interest to all savings accounts
  7. Display all customers and accounts
  8. Show transaction history
  9. Exit

* Each operation should:

  * Accept user input (via `Scanner`)
  * Validate data (e.g., no negative amounts, valid account numbers)
  * Handle edge cases gracefully (e.g., insufficient funds)

* Example interaction:

  ```
  === SMARTBANK SYSTEM ===
  1. Create Customer
  2. Open Account
  3. Deposit
  ...
  Enter choice: 1
  Enter customer name: Alice
  Enter email: alice@mail.com
  Enter phone: 9876543210
  ✅ Customer created with ID: C1001
  ```

Focus:

* **System design and flow control**
* **Dynamic input handling**
* **Object collaboration**
* **Error-proof design**

---

## 🚀 **Optional Level 5 — (Bonus Challenge)**

If you master Level 4, try these:

1. 💾 **Persist Data** — Save all transactions and accounts to a file using Java I/O or serialization.
2. 🔐 **Authentication** — Add a simple login system (Customer ID + PIN).
3. 📈 **Analytics** — Generate small reports:

   * Highest balance account
   * Total bank deposits
   * Total interest paid
4. 💳 **Upgrade/Downgrade Account** — Convert between savings/current if eligible.

---

### 💡 Key Concepts Reinforced

* Class Design & Abstraction
* Inheritance + Method Overriding
* Polymorphism via `BankAccount` references
* Composition (`Bank` holds `Customers` and `Accounts`)
* Encapsulation & Protected Helpers
* Realistic Calculations & Validations
* Interactive console-based flow

---

Would you like me to next break this down into a **Level 1-only version (first coding step)** like before, so you can start cleanly and build it step by step?
