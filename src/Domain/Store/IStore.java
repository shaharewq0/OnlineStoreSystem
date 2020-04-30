package Domain.Store;

import java.util.Collection;
import java.util.List;

import Domain.RedClasses.IUser;
import Domain.info.ProductDetails;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;

public interface IStore {
	public String getName();

	public Collection<Product> getProducts();

	public String getAddress();

	public boolean addProduct(Product p);

	public Product findProductByName(String name);

	public List<Product> findProductByCategory(String category);

	public List<Product> findProductByKeyword(String keyword);

	public int getRating();


	public boolean appointOwner(IUser user);

	public boolean appointManager(IUser user);

	public boolean fireManager(IUser user);

	public List<PurchaseDetails> viewPurchaseHistory() throws Exception;

	public Boolean CheckItemAvailable(ProductDetails items);

	public double getPrice(String item);

	public Product TakeItem(String name, int amout);

	public List<Discount> getDiscounts(String name);

	boolean removeProduct(String pName);

	boolean editProduct(String OLD_p, Product NEW_p);
}
