package Domain.Store;


import java.util.List;

public interface IshoppingCart {
    public List<shoppingBasket> getBaskets();
    public boolean addBasket(shoppingBasket b);
    public List<MyPair<Product,Integer>> allProductsInCart();
    public shoppingBasket findBasket(StoreImp s);
}
