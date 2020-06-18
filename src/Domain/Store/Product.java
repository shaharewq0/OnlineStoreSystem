package Domain.Store;

import Domain.info.ProductDetails;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Product extends Object implements IProduct {
    private int id;
    private String name;
    private List<String> category;
    private List<String> keyWords;
    private double price;
    private int rating;
    //will change to be single
    private Product_boundle Amount = new Product_boundle();
    // private String storename;
    //private int amount;

    public Product(String name, List<String> category, List<String> keyWords, double price, int rating) {
        this.name = name;
        this.category = new LinkedList<String>();
        this.category.addAll(category);
        this.keyWords = keyWords;
        this.price = price;
        this.rating = rating;
        //this.storename = storename;
        //this.store=store;
        //amount=0;
    }

    public Product(ProductDetails p) {
        name = p.getName();
        category = new LinkedList<>(p.getCategory());
        keyWords = new LinkedList<>(p.getKeyWords());
        price = p.getPrice();
        rating = p.getRating();
        Amount.add(p.getAmount());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Product_boundle amount) {
        Amount = amount;
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

//    public String getStore() {
//        return storename;
//    }


    public void edit(Product p) {
        this.price = p.price;
        this.category = p.category;
        this.name = p.name;
        this.keyWords = p.keyWords;
        this.rating = p.rating;
    }

    public void addToAmount(int add) {

        Amount.add(add);
    }

    public int getAmount() {
        return Amount.size();
        //return amount;
    }

    public int removeAmount(int amount) {
        if (Amount.size() < amount) {
            int temp = Amount.size();
            Amount.remove(temp);
            return temp;
        } else {
            Amount.remove(amount);
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

        //output += "stroe:" + storename + "\n";
        return output;

    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Product))
            return false;

        Product p = (Product) other;
        if (!(this.name == p.name &
                this.price == p.price &
                this.rating == p.rating &
                this.category.size() == p.category.size() &
                this.keyWords.size() == p.keyWords.size()))
            return false;

        for (String cat : category) {
            if (!p.category.contains(cat))
                return false;
        }
        for (String Key : keyWords) {
            if (!p.keyWords.contains(Key))
                return false;
        }

        //will change to be single
        //TODO add check amount
      //  Stack<concreate_Product> Amount = new Stack<>();
        return true;

    }

}
