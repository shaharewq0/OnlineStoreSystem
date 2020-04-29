package Domain.Store;


import java.util.Collection;
import java.util.List;

import tests.AcceptanceTests.auxiliary.ProductDetails;

public interface IshoppingCart {
    public Collection<shoppingBasket> getBaskets();
    public double  CalcPrice();
   // public boolean addBasket(shoppingBasket b);
    public List<ProductDetails> allProductsInCart();
    //public shoppingBasket findBasket(StoreImp s);
    public List<Product> getItems();
}
