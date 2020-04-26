package Domain.Store;


import java.util.List;



public interface IshoppingBasket {
    public void addProduct(Product p);
    public StoreImp getStore();
    public List<MyPair<Product, Integer>> getProducts();
    public int removeProduct(Product p , int num);
}