package Store;

import java.util.List;

public class Product implements IProduct{
    private String name;
    private String category;
    private List<String> keyWords;
    private int price;
    private int rating;
    private Store store;

    public Product(String name , String category, List<String> keyWords , int price , int rating, Store store) {
        this.name = name;
        this.category=category;
        this.keyWords=keyWords;
        this.price=price;
        this.rating=rating;
        this.store=store;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public int getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    public Store getStore() {
        return store;
    }
}
