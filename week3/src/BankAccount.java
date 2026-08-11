public class BankAccount {

    private int balance;
    private boolean isOpen;

    public synchronized void open() throws BankAccountActionInvalidException {
        if (isOpen) {
            throw new BankAccountActionInvalidException("Account already open");
        }
        this.balance = 0;
        this.isOpen = true;
    }

    public synchronized void close() throws BankAccountActionInvalidException {
        if (!isOpen) {
            throw new BankAccountActionInvalidException("Account already closed");
        }
        this.isOpen = false;
    }

    public synchronized int getBalance() throws BankAccountActionInvalidException {
        ensureAccountIsOpen();
        return this.balance;
    }

    public synchronized void deposit(int amount) throws BankAccountActionInvalidException {
        ensureAccountIsOpen();
        if (amount < 0) {
            throw new BankAccountActionInvalidException("Cannot deposit negative amount");
        }
        this.balance += amount;
    }

    public synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
        ensureAccountIsOpen();
        if (amount < 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw negative amount");
        }
        if (amount > this.balance) {
            throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        }
        this.balance -= amount;
    }

    private void ensureAccountIsOpen() throws BankAccountActionInvalidException {
        if (!isOpen) {
            throw new BankAccountActionInvalidException("Account closed");
        }
    }

    public static void main(String[] args) {
        try {
            BankAccount account = new BankAccount();

            System.out.println("--- Starting Bank Account Test ---");
            account.open();
            System.out.println("1. Account successfully opened.");

            account.deposit(500);
            System.out.println("2. Deposited $500. Current balance: " + account.getBalance());

            account.withdraw(200);
            System.out.println("3. Withdrew $200. Current balance: " + account.getBalance());

            account.close();
            System.out.println("4. Account closed successfully.");

        } catch (BankAccountActionInvalidException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class BankAccountActionInvalidException extends Exception {
    public BankAccountActionInvalidException(String message) {
        super(message);
    }
}