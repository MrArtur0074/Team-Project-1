package solid_dz2;

public class FixedAccount extends BankAccount{

    public FixedAccount(double balance) {
        super(balance);
    }

    @Override
    public double calculateInterest() {
        return balance * 0.06;
    }
}
