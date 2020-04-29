package Domain.Store;

import java.util.List;
import java.util.Map;

import tests.AcceptanceTests.auxiliary.ProductDetails;

public interface IshoppingBasket {
	public void addProduct(String name, int amount);

	public int removeProduct(String name, int num);

	public StoreImp getStore();

	public List<ProductDetails> getProducts();
	public double CalcPrice();
	public List<Product> getItems();

}
