package Domain.Policies.Discounts;

import Domain.Logs.ErrorLogger;
import Domain.Store.Product;
import Domain.Store.Product_boundle;

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

    static Product_boundle findproduct(List<Product_boundle> PB,Product p) {
        for (Product_boundle pb : PB) {
            if(pb.item.equals(p))
                return pb;
        }
        return null;
    }

    @Override
    public boolean hasDiscount(List<Product_boundle> products) {
        if (LocalDate.now().isAfter(expirationDate))
            return false;
        if (productName.equals("ALL"))
            return true;
        return findproduct(products,product) != null;//products.contains(product);
    }

    @Override
    public double applyDiscount(List<Product_boundle>  products) {
        if (productName.equals("ALL")) {
            double price = 0;
            for (Product_boundle entry : products)
                price += entry.size() * entry.item.getPrice();
            return price * percentage;
        }

        return findproduct(products,product) !=null ? 0 : findproduct(products,product).size() * product.getPrice() * percentage;
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
