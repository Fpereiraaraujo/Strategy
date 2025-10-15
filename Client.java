import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        PaymentContext context = new PaymentContext();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o método de pagamento:");
        System.out.println("1 - Cartão de Crédito");
        System.out.println("2 - PayPal");
        System.out.println("3 - Transferência Bancária");
        int option = scanner.nextInt();

        System.out.print("Digite o valor do pagamento: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // limpar buffer

        switch (option) {
            case 1:
                System.out.print("Nome no cartão: ");
                String holder = scanner.nextLine();

                System.out.print("Número do cartão: ");
                String number = scanner.nextLine();

                System.out.print("Validade (MM/AA): ");
                String expiry = scanner.nextLine();

                context.setPaymentStrategy(new CreditCardPayment(number, holder, expiry));
                break;

            case 2:
                System.out.print("E-mail do PayPal: ");
                String email = scanner.nextLine();

                context.setPaymentStrategy(new PayPalPayment(email));
                break;

            case 3:
                System.out.print("Número da conta bancária: ");
                String account = scanner.nextLine();

                context.setPaymentStrategy(new BankTransferPayment(account));
                break;

            default:
                System.out.println("Opção inválida!");
                scanner.close();
                return;
        }

        context.processPayment(amount);
        scanner.close();
    }
}
