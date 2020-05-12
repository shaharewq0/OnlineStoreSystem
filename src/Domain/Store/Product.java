package Domain.Store;

import java.util.LinkedList;
import java.util.List;

import Domain.info.ProductDetails;

public class Product extends Object  implements IProduct{
    private String name;
    private List<String> category;
    private List<String> keyWords;
    private double price;
    private int rating;
    private StoreImp store;
    private int amount;

    public Product(String name , List<String> category, List<String> keyWords , double price , int rating, StoreImp store) {
        this.name = name;
        this.category=new LinkedList<String>();
        this.category.addAll(category);
        
        this.keyWords=keyWords;
        this.price=price;
        this.rating=rating;
        this.store=store;
        amount=0;
    }

    public Product(ProductDetails p) {
		name = p.getName();
		category.addAll(p.getCategory());
		keyWords.addAll(p.getKeyWords());
		price = p.getPrice();
		rating = 0;
		amount = p.getAmount();
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

    public void addToAmount(int add){
        amount+=add;
    }

    public int getAmount(){
        return amount;
    }

    public int removeAmount(int amount){
        if(this.amount<amount){
            this.amount=0;
            return this.amount;
        }else{
            this.amount=this.amount-amount;
            return amount;
        }
    }

    @Override
    public String toString()
    {
    	String output ="";
    	output +="name:"+ name + "\n";
    	output +="category:"+ category + "\n";
    	output +="rating:"+ rating + "\n";
    	output +="amount:"+ amount + "\n";
    	output +="price:"+ price + "\n";
    	return output;
    			
    }
    
}
