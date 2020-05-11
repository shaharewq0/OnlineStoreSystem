package Domain.Store.workers;

import java.util.List;

import Domain.RedClasses.IUser;
import Domain.RedClasses.User;
import Domain.Store.Product;
import Domain.Store.Purchase;
import Domain.info.ProductDetails;
import Domain.info.Question;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;

public interface Store_role {
    boolean addItem(Product item);
	boolean addItem(ProductDetails item);
   // boolean removeItem(Product item);
    boolean appointOwner(IUser user);
    boolean appointManager(IUser user);
   // <T> void setPremissions(IUser manager, List<T> Permissions); //generic for now
    boolean fire(IUser manager);
	boolean editItem(String OLD_item, Product NEW_item);
	boolean removeItem(String prodactname);
	List<Purchase> getPurchaseHistory();
	boolean editManagerPermesions(String managername, List<String> permesions);
	//permesions--
	List<Question> viewQuestions();
	boolean giveRespond(String ansewr, int qustionID);

	boolean canPromoteToOwner();
	
}
