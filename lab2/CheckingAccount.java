public class CheckingAccount extends Account {

    private int count;
    private static final int FREE_TRANSACTIONS = 5;

    public CheckingAccount(int accNumber) {
        super(accNumber);
        count = 0;
    }

    @Override
    public void deposit(double sum) {
        super.deposit(sum);
        count++;
    }

    @Override
    public void withdraw(double sum) {
        super.withdraw(sum);
        count++;
    }

    public void deductFee() {
        if (count > FREE_TRANSACTIONS) {
            int extra = count - FREE_TRANSACTIONS;
            double fee = extra * 0.02;
            super.withdraw(fee);
        }
        count = 0;
    }

    @Override
    public String toString() {
        return "CheckingAccount: " + getAccountNumber() +
               " Balance: " + getBalance() +
               " Transactions: " + count;
    }
}