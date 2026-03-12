public class BankTest {
    public static void main(String[] args) {

        Bank bank = new Bank();

        SavingsAccount s1 = new SavingsAccount(1, 5);
        CheckingAccount c1 = new CheckingAccount(2);
        CheckingAccount c2 = new CheckingAccount(3);

        bank.openAccount(s1);
        bank.openAccount(c1);
        bank.openAccount(c2);

        s1.deposit(100);
        c1.deposit(50);
        c1.withdraw(80);
        c1.deposit(100);
        c1.withdraw(20);
        c1.deposit(5);
        c2.deposit(1000);
        c1.transfer(20,c2);
        c1.deposit(10);

        bank.update();

        bank.printAccounts();
    }
}