package Domain.UserClasses;


import java.util.Collection;
import java.util.List;

import Domain.Store.MyPair;
import Domain.Store.Product;
import Domain.info.ProductDetails;

public interface IshoppingCart {
     Collection<shoppingBasket> getBasketsValues();
     double  CalcPrice();
   // public boolean addBasket(shoppingBasket b);
     List<ProductDetails> allProductsInCart();
    //public shoppingBasket findBasket(StoreImp s);
}
