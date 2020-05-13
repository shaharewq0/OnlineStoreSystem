package Domain.Store.workers;

import java.util.Collection;
import java.util.List;

import Domain.RedClasses.IUser;
import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import Domain.info.Question;

public interface Store_role {
	boolean addItem(Product item);

	boolean addItem(ProductDetails item);

	boolean appointOwner(IUser user);

	boolean appointManager(IUser user);

	boolean fire(String manager);

	boolean editItem(String OLD_item, Product NEW_item);

	boolean removeItem(String prodactname);

	public List<StorePurchase> getPurchaseHistory();

	boolean editManagerPermesions(String managername, List<String> permesions);

	public Collection<Question> viewQuestions();

	boolean giveRespond(String ansewr, int qustionID);

	boolean canPromoteToOwner();

	public StoreImp getStore();

	boolean getfire();

	boolean IgotFire(String worker);

	boolean getNewPermesions(List<String> Permesions);

	public String getName();

}
