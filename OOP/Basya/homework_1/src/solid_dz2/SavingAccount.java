package solid_dz2;

public class SavingAccount extends BankAccount{

    public SavingAccount(double balance) {
        super(balance);
    }

    @Override
    public double calculateInterest() {
        return balance * 0.04;
    }
}
