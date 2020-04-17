package Store;

import java.util.List;

public class Store {
    private String name;
    private List<Product> products;
    private String address;

    public Store(String name, List<Product> products, String address) {
        this.name = name;
        this.products = products;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getAddress() {
        return address;
    }

    public boolean addProduct(Product p){
        if (products.contains(p)){
            return false;
        }
        products.add(p);
        return true;
    }
}
