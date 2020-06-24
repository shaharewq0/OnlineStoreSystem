package Domain.Store.workers;

import Domain.Policies.Acquisitions.Acquisition;
import Domain.Policies.Discounts.Discount;
import Domain.Store.Product;
import Domain.Store.Product_boundle;
import Domain.Store.StoreImp;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.store_System.Roles.Registered;

import java.util.Collection;
import java.util.List;

public interface Store_role {

    boolean addItem(Product_boundle item);

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

    List<String> getManagerPermesions();

    Collection<Question> viewQuestions();

    boolean giveRespond(String ansewr, int qustionID);

    boolean canPromoteToOwner();

    StoreImp getStore();

    boolean getfire();

    boolean IgotFire(String worker);

    boolean getNewPermesions(List<String> Permesions);

    boolean addDiscount(Discount discount);

    String getDiscounts();

    boolean removeDiscount(int discountID);

    String getName();

    boolean addacquisition(Acquisition acquisition);

    boolean removeacquisition(int acquisitionID);

    String getAcquisition();

    String getType();
}
