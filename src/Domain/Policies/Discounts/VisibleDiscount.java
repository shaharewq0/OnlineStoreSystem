package Domain.Policies.Discounts;

import Domain.Logs.ErrorLogger;
import Domain.Store.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class VisibleDiscount implements Discount {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    protected String productName;
    protected Product product;
    LocalDate expirationDate;
    private int percentage;

    VisibleDiscount(String productName, int percentage, LocalDate expirationDate) {
        this.productName = productName;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean hasDiscount(Map<Product, Integer> products) {
        if (LocalDate.now().isAfter(expirationDate))
            return false;
        if (productName.equals("ALL"))
            return true;
        return products.containsKey(product);
    }

    @Override
    public double applyDiscount(Map<Product, Integer> products) {
        if (productName.equals("ALL")) {
            double price = 0;
            for (Map.Entry<Product, Integer> entry : products.entrySet())
                price += entry.getValue() * entry.getKey().getPrice();
            return price * percentage;
        }
        return products.containsKey(product) ? 0 : products.get(product) * product.getPrice() * percentage;
    }

    @Override
    public List<String> getProductsNames() {
        return Collections.singletonList(productName);
    }

    @Override
    public void replaceProducts(List<Product> products) {
        if (products.size() != 1)
            ErrorLogger.GetInstance().Add_Log("IN Discount : replace product error");
        product = products.get(0);
    }

    @Override
    public String toString() {
        return "Discount on '" + productName + "': " +
                percentage + "% until " + expirationDate.format(format);
    }
}
