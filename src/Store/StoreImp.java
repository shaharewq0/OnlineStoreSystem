package Store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StoreImp implements IStore {
    private String name;
    private List<Product> products;
    private String address;
    private int rating;

    private Map<IUser, List<IshoppingBasket>> purcheses;

    public StoreImp(String name, List<Product> products, String address, int rating) {
        this.name = name;
        this.products = products;
        this.address = address;
        this.rating=rating;

        purcheses = new HashMap<>();
    }

    public StoreImp(String name, String address,int rating) {
        this.name = name;
        this.products = new LinkedList<>();
        this.address = address;
        this.rating=rating;

        purcheses = new HashMap<>();
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
        if (contains(p,products) | !p.getStore().getName().equals(name)){
            return false;
        }
        products.add(p);
        return true;
    }

    private boolean contains(Product p, List<Product> products){
        for(Product current:products){
            if(current.getName().equals(p.getName())){
                return true;
            }
        }
        return false;
    }

    public Product findProductByName(String name){
        for(Product p : products){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
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
