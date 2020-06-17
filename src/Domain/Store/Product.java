package Domain.Store;

import Domain.info.ProductDetails;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Product extends Object implements IProduct {
    private String name;
    private List<String> category;
    private List<String> keyWords;
    private double price;
    private int rating;
    private Stack<concreate_Product> Amount = new Stack<>();
    private String storename;
    //private int amount;

    public Product(String name, List<String> category, List<String> keyWords, double price, int rating, String storename) {
        this.name = name;
        this.category = new LinkedList<String>();
        this.category.addAll(category);
        this.keyWords = keyWords;
        this.price = price;
        this.rating = rating;
        this.storename = storename;
        //this.store=store;
        //amount=0;
    }

    public Product(ProductDetails p) {
        name = p.getName();
        category = new LinkedList<>(p.getCategory());
        keyWords = new LinkedList<>(p.getKeyWords());
        storename = p.getStoreName();
        price = p.getPrice();
        rating = p.getRating();
        for (int i = 0; i < p.getAmount(); i++)
            Amount.add(new concreate_Product());
        //amount = p.getAmount();
        //store = System.getInstance().getStoreDetails(p.getStoreName());
        storename = p.getStoreName();
    }



    public String getName() {
        return name;
    }

    public List<String> getCategory() {
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

    public String getStore() {
        return storename;
    }
//
//    public boolean compare(Product p) {
//        return this.name.compareTo(p.name) == 0 && this.category == p.category && this.storename.compareTo(p.storename) == 0 && this.price == p.price && this.rating == p.rating;
//    }

    public void edit(Product p) {
        this.price = p.price;
        this.category = p.category;
        this.name = p.name;
        this.keyWords = p.keyWords;
        this.rating = p.rating;
    }

    public void addToAmount(int add) {

        for (int i = 0; i < add; i++)
            Amount.add(new concreate_Product());
        // amount+=add;
    }

    public int getAmount() {
        return Amount.size();
        //return amount;
    }

    public int removeAmount(int amount) {
        if (Amount.size() < amount) {
            int temp = Amount.size();
            Amount = new Stack<>();
            return temp;
        } else {
            for (int i = 0; i < amount; i++)
                Amount.pop();
            // this.amount = this.amount - amount;
            return amount;
        }
    }

    @Override
    public String toString() {
        String output = "";
        output += "name:" + name + "\n";
        output += "category:" + category + "\n";
        output += "rating:" + rating + "\n";
        output += "amount:" + Amount.size() + "\n";
        output += "price:" + price + "\n";
        output += "stroe:" + storename + "\n";
        return output;

    }

}
