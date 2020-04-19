package store_System;

import Store.Store;
import Store.shoppingCart;
import Store.Product;
import java.util.List;

public interface ISystem {
    public boolean register(int id, String password);
    public boolean login(int id, String password);
    public Store getStoreDetails(String name);
    public List<Store> getAllStores();
    public List<Product> getProductsFromStore(String name);
    public List<Product> searchProductsByName(String name);
    public List<Product> searchProductsByCategory(String category);
    public List<Product> searchProductsByKeyword(String keyword);
    public List<Product> filterByPrice(List<Product> base , double min , double max);
    public List<Product> filterByRating(List<Product> base , int min , int max);
    public List<Product> filterByCategory(List<Product> base , String category);
    public List<Product> filterByStoreRating(List<Product> base , int min, int max);
    public boolean purchase(shoppingCart cart, int creditCard, String address);
    public boolean memberPurchase(int id,shoppingCart cart,int creditCard,String address);
    public boolean logout(int id);
    public Store openStore(String name, String address, int rating);
    public List<shoppingCart> orderHistory(int id);
}
