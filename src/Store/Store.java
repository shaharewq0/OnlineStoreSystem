package Store;

import java.util.LinkedList;
import java.util.List;

public class Store implements IStore {
    private String name;
    private List<Product> products;
    private String address;
    private int rating;

    public Store(String name, List<Product> products, String address, int rating) {
        this.name = name;
        this.products = products;
        this.address = address;
        this.rating=rating;
    }

    public Store(String name, String address,int rating) {
        this.name = name;
        this.products = new LinkedList<>();
        this.address = address;
        this.rating=rating;
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

    public int getRating() {
        return rating;
    }

    public boolean addProduct(Product p){
        if (products.contains(p) | !p.getStore().getName().equals(name)){
            return false;
        }
        products.add(p);
        return true;
    }

    public List<Product> findProductByName(String name){
        List<Product> toReturn = new LinkedList<>();
        for(Product p : products){
            if(p.getName().equals(name)){
                toReturn.add(p);
            }
        }
        return toReturn;
    }

    public List<Product> findProductByCategory(String category){
        List<Product> toReturn = new LinkedList<>();
        for(Product p : products){
            if(p.getCategory().equals(category)){
                toReturn.add(p);
            }
        }
        return toReturn;
    }

    public List<Product> findProductByKeyword(String keyword){
        List<Product> toReturn = new LinkedList<>();
        for(Product p : products){
            if(p.getKeyWords().contains(keyword)){
                toReturn.add(p);
            }
        }
        return toReturn;
    }
}
