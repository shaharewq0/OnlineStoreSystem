package tests.AcceptanceTests.auxiliary;

public class DummyPayment {
    private String paymentMethod;

    public DummyPayment() {
        paymentMethod = "default";
    }

    public DummyPayment(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String pay(String card) {
        return "approved";
    }
}
