package Domain.info;


import Domain.Store.Product;

import java.util.*;

public class ProductDetails {
    public String getName() {
        return name;
    }

    private List<String> keyWords;
    private String name;
    private List<String> category;
    private String storeName;
    private int amount;
    private int rating;
    private double price;

    public ProductDetails(String name, List<String> category, String storeName, int amount, double price) {
        this.name = name;
        this.category = new LinkedList<>();
        this.category.addAll(category);
        this.storeName = storeName;
        this.amount = amount;
        this.price = price;
        this.keyWords = Arrays.asList(name.split(" "));
        this.rating = 0;
    }

    public ProductDetails(String name, List<String> category, List<String> kewwords, String storeName, int amount, double price) {
        this.name = name;
        this.category = new LinkedList<>();
        this.category.addAll(category);
        this.storeName = storeName;
        this.amount = amount;
        this.price = price;
        this.keyWords = kewwords;
        this.rating = 0;
    }

    public ProductDetails(String name, List<String> category, String storeName, int amount, double price, int rating) {
        this.name = name;
        this.category = new LinkedList<>();
        this.category.addAll(category);
        this.storeName = storeName;
        this.amount = amount;
        this.price = price;
        this.keyWords = Arrays.asList(name.split(" "));
        this.rating = rating;
    }

    public ProductDetails(Product pro, int amount) {
        this.name = pro.getName();
        this.category = new LinkedList<String>();
        category.addAll(pro.getCategory());
        this.storeName = pro.getStore();
        this.amount = amount;
        keyWords = new LinkedList<>(pro.getKeyWords());
        this.price = pro.getPrice();
        this.rating = pro.getRating();
    }


    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return price * amount;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public List<String> getCategory() {
        return category;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRating() {
        return rating;
    }

    static public List<ProductDetails> adapteProdactList(Collection<Product> list) {
        LinkedList<ProductDetails> output = new LinkedList<ProductDetails>();
        for (Product product : list) {
            output.add(new ProductDetails(product, product.getAmount()));
        }
        return output;
    }

    static public ProductDetails Copy(ProductDetails other) {
        return new ProductDetails(other.name, other.getCategory(), other.getStoreName(), other.getAmount(), other.price, other.getRating());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetails details = (ProductDetails) o;
        return //getAmount() == details.getAmount() &&
                //getRating() == details.getRating() &&
                //Double.compare(details.getPrice(), getPrice()) == 0 &&
                //Objects.equals(getKeyWords(), details.getKeyWords()) &&
                Objects.equals(getName(), details.getName()) &&
                //Objects.equals(getCategory(), details.getCategory()) &&
                Objects.equals(getStoreName(), details.getStoreName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeyWords(), getName(), getCategory(), getStoreName(), getAmount(), getRating());
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "keyWords=" + keyWords +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", storeName='" + storeName + '\'' +
                ", amount=" + amount +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }
}
