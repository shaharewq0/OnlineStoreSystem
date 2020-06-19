package Domain.Store.workers;

import java.util.Collection;
import java.util.List;

import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.store_System.Roles.Registered;

public interface Store_role {
    boolean addItem(Product item);

    boolean addItem(ProductDetails item);

   // boolean appointOwner(IUser user);

    boolean appointOwner(Registered user);

  //  boolean appointManager(IUser user);

    boolean appointManager(Registered user);

    boolean fireOwner(String Owner);

    boolean fireManager(String manager);

    boolean editItem(String OLD_item, Product NEW_item);

    boolean removeItem(String prodactname);

    List<StorePurchase> getPurchaseHistory();

    boolean editManagerPermesions(String managername, List<String> permesions);

    Collection<Question> viewQuestions();

    boolean giveRespond(String ansewr, int qustionID);

    boolean canPromoteToOwner();

    StoreImp getStore();

    boolean getfire();

    boolean IgotFire(String worker);

    boolean getNewPermesions(List<String> Permesions);

    boolean addDiscount(String discount);

    boolean removeDiscount(int discountID);

    String getName();

    boolean addacquisition(String acquisition);

    boolean removeacquisition(int acquisitionID);
}
