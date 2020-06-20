package Domain.UserClasses;

import java.util.List;

import Domain.Store.MyPair;
import Domain.Store.Product;
import Domain.Store.Product_boundle;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;

public interface IshoppingBasket {
	 boolean addProduct(String name, int amount);

	 int removeProduct(String name, int num);

	 StoreImp getStore();

	 List<ProductDetails> getProducts();
	 double CalcPrice();
	 List<MyPair<Product_boundle,String>> getItems();

}
