package Domain.Policies.Discounts;

import Domain.info.ProductDetails;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

class VisibleDiscount implements Discount {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    protected String productName;
    LocalDate expirationDate;
    private int percentage;

    VisibleDiscount(String productName, int percentage, LocalDate expirationDate) {
        this.productName = productName;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    /**
     * returns the product 'productName' from 'products'
     * returns null if doesn't exist
     */
    ProductDetails getDiscountProduct(List<ProductDetails> products) {
        for (ProductDetails p : products) {
            if (p.getName().equals(productName))
                return p;
        }
        return null;
    }

    @Override
    public boolean hasDiscount(List<ProductDetails> products) {
        if (LocalDate.now().isAfter(expirationDate))
            return false;
        if (productName.equals("ALL"))
            return true;
        return getDiscountProduct(products) != null;
    }

    @Override
    public double applyDiscount(List<ProductDetails> products) {
        if (productName.equals("ALL")) {
            double price = 0;
            for (ProductDetails p : products)
                price += p.getAmount() * p.getPrice();
            return price * percentage;
        }
        ProductDetails p = getDiscountProduct(products);
        return p == null ? 0 : p.getAmount() * p.getPrice() * percentage;
    }

    @Override
    public String toString() {
        return "Discount on '" + productName + "': " +
                percentage + "% until " + expirationDate.format(format);
    }
}
