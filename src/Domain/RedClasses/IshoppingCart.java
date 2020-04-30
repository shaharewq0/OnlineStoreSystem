package Domain.RedClasses;


import java.util.Collection;
import java.util.List;

import Domain.Store.Product;
import Domain.info.ProductDetails;

public interface IshoppingCart {
    public Collection<shoppingBasket> getBaskets();
    public double  CalcPrice();
   // public boolean addBasket(shoppingBasket b);
    public List<ProductDetails> allProductsInCart();
    //public shoppingBasket findBasket(StoreImp s);
    public List<Product> getItems();
}
