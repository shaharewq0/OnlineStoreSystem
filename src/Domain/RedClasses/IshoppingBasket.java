package Domain.RedClasses;

import java.util.List;

import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;

public interface IshoppingBasket {
	 void addProduct(String name, int amount);

	 int removeProduct(String name, int num);

	 StoreImp getStore();

	 List<ProductDetails> getProducts();
	 double CalcPrice();
	 List<Product> getItems();

}
