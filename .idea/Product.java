public interface Product {
    String getName();
    double getPrice();
}
public class ConcreteProduct implements Product {
    private String name;
    private double price;

    public ConcreteProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}



public class Order {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}




public interface PaymentGateway {
    void processPayment(double amount);
}
public class PaymentGatewayAdapter implements PaymentGateway {
    private ThirdPartyPaymentService paymentService;

    public PaymentGatewayAdapter(ThirdPartyPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void processPayment(double amount) {
        // Adapter pattern: вызываем методы сторонней системы, адаптируя их к интерфейсу PaymentGateway
        paymentService.initiatePayment(amount);
    }
}



public class OnlineStore {
    private CatalogModule catalog = new CatalogModule();
    private OrderModule order = new OrderModule();
    private PaymentModule payment = new PaymentModule();

    public void purchaseProduct(String productName) {
        Product product = catalog.findProduct(productName);
        order.addProduct(product);

        double totalPrice = order.calculateTotal();
        payment.processPayment(totalPrice);
    }
}



