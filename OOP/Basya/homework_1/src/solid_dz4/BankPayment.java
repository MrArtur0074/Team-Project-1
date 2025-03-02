package solid_dz4;

public class BankPayment implements PaymentBankTransfer{
    @Override
    public void payWithBankTransfer (double amount) {
        System.out.println("Paying with bank transfer: $" + amount);
    }
}
