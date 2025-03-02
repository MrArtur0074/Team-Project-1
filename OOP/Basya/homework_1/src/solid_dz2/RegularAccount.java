package solid_dz2;

public class RegularAccount extends BankAccount{

    public RegularAccount(double balance) {
        super(balance);
    }

    @Override
    public double calculateInterest() {
        return 0;
    }
}
