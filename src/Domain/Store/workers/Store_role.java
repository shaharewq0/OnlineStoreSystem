package Domain.Store.workers;

import java.util.List;

import Domain.RedClasses.IUser;
import Domain.Store.Product;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;

public interface Store_role {
    boolean addItem(Product item);
    boolean removeItem(Product item);
    boolean appointOwner(IUser user);
    boolean appointManager(IUser user);
    <T> void setPremissions(IUser manager, List<T> Permissions); //generic for now
    boolean fire(IUser manager);
    List<PurchaseDetails>  viewPurchaseHistory();
	boolean editItem(String OLD_item, Product NEW_item);
	boolean removeItem(String prodactname);
}
