package Domain.RedClasses;

import java.util.List;
import java.util.Map;

import Domain.Store.Product;
import Domain.Store.StoreImp;
import tests.AcceptanceTests.auxiliary.ProductDetails;

public interface IshoppingBasket {
	public void addProduct(String name, int amount);

	public int removeProduct(String name, int num);

	public StoreImp getStore();

	public List<ProductDetails> getProducts();
	public double CalcPrice();
	public List<Product> getItems();

}
