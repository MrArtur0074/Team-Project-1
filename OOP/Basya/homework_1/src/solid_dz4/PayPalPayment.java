package solid_dz4;

public class PayPalPayment implements PaymentCard{
    @Override
    public void payWithCard (double amount) {
        System.out.println("Paying with PayPal card: $" + amount);
    }
}
