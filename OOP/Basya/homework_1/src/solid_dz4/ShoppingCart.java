package solid_dz4;

public class ShoppingCart {
    private PaymentCard payment;

    public ShoppingCart (PaymentCard payment) {
        this.payment = payment;
    }

    public PaymentCard getPayment() {
        return payment;
    }

    public void setPayment(PaymentCard payment) {
        this.payment = payment;
    }

    public void checkout (double amount) {
        payment.payWithCard(amount);
    }
}
