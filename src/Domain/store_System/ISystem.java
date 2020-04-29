package Domain.store_System;

import java.util.List;

import Domain.Store.*;
import tests.AcceptanceTests.auxiliary.ProductDetails;

public interface ISystem {
    public boolean register(String id, String password);
    public Registered login(String id, String password);
    public StoreImp getStoreDetails(String name);
    public List<StoreImp> getAllStores();
    public List<Product> getProductsFromStore(String name);
    public List<Product> searchProductsByName(String name);
    public List<Product> searchProductsByCategory(String category);
    public List<Product> searchProductsByKeyword(String keyword);
    public List<Product> filterByPrice(List<Product> base , double min , double max);
    public List<Product> filterByRating(List<Product> base , int min , int max);
    public List<Product> filterByCategory(List<Product> base , String category);
    public List<Product> filterByStoreRating(List<Product> base , int min, int max);
    public boolean purchase(shoppingCart cart, int creditCard, String address);
    public boolean memberPurchase(String id,shoppingCart cart,int creditCard,String address);
    public StoreImp openStore(String name, String address, int rating);
    public List<shoppingCart> orderHistory(String id);
    public List<ProductDetails> CheckItemAvailable(List<ProductDetails> items);
    public double calcPrice(shoppingCart c);
    public List<IshoppingBasket> orderHistory(IStore store);
}
