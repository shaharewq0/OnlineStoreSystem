package Store;

import java.util.List;

public class Product implements IProduct{
    private String name;
    private String category;
    private List<String> keyWords;
    private double price;
    private int rating;
    private StoreImp store;

    public Product(String name , String category, List<String> keyWords , double price , int rating, StoreImp store) {
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

    public double getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    public StoreImp getStore() {
        return store;
    }

    public boolean compare(Product p){
        return this.name==p.name && this.category==p.category && this.store==p.store && this.price==p.price && this.rating==p.rating;
    }

    public void edit(Product p){
        this.price=p.price;
        this.category=p.category;
        this.name=p.name;
        this.keyWords=p.keyWords;
        this.rating=p.rating;
    }


}
