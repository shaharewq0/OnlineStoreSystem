package Domain.info;


import Domain.Store.Product;
import Domain.Store.Product_boundle;

import java.util.*;

public class ProductDetails {

    private int id;
    private List<String> keyWords;
    private String name;
    private List<String> category;
    private String storeName;
    private int amount;
    private int rating;
    private double price;

    public ProductDetails(){}

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

    public ProductDetails(Product pro, int amount,String storeName) {
        this.name = pro.getName();
        this.category = new LinkedList<String>();
        category.addAll(pro.getCategory());
        this.storeName = storeName;//pro.getStore();
        this.amount = amount;
        keyWords = new LinkedList<>(pro.getKeyWords());
        this.price = pro.getPrice();
        this.rating = pro.getRating();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    static public List<ProductDetails> adapteProdactList(Collection<Product_boundle> list, String storeName) {
        LinkedList<ProductDetails> output = new LinkedList<ProductDetails>();
        for (Product_boundle product : list) {
            output.add(new ProductDetails(product.item, product.size(),storeName));
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
        return getAmount() == details.getAmount() &&
                //getRating() == details.getRating() &&
                Double.compare(details.getPrice(), getPrice()) == 0 &&
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
