public class CreditCardPayment implements PaymentStrategy {

    private String cardNumber;
    private String cardHolder;
    private String expiryDate;

    public CreditCardPayment(String cardNumber, String cardHolder, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Pagamento de R$" + amount + " realizado com Cartão de Crédito: " + cardHolder);
    }
}
