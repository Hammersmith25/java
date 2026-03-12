import java.util.Vector;

public class Bank {

    private Vector<Account> accounts;

    public Bank() {
        accounts = new Vector<>();
    }

    public void openAccount(Account acc) {
        accounts.add(acc);
    }

        public void closeAccount(int accNumber) {
            for (Account acc : accounts) {
                if (acc.getAccountNumber() == accNumber) {
                    accounts.remove(acc);
                    break;
                }
            }
        }

    public void update() {
        for (Account acc : accounts) {

            if (acc instanceof SavingsAccount) {
                ((SavingsAccount) acc).addInterest();
            }

            if (acc instanceof CheckingAccount) {
                ((CheckingAccount) acc).deductFee();
            }
        }
    }

    public void printAccounts() {
        for (Account acc : accounts) {
            acc.print();
        }
    }

    
}