public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(int accNumber, double rate) {
        super(accNumber);
        interestRate = rate;
    }

    public void addInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }

    @Override
    public String toString() {
        return "SavingsAccount:" + getAccountNumber() +
               " Balance: " + getBalance() +
               " Interest rate: " + interestRate + "%";
    }
}