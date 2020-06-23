package Service_Layer.owner_accese;

import Domain.Store.Product_boundle;
import Domain.UserClasses.User;
import Domain.Policies.Acquisitions.Acquisition;
import Domain.Policies.Discounts.Discount;
import Domain.Store.Product;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import Domain.UserClasses.User;
import Domain.info.Question;
import Domain.store_System.System;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class owner_accese {

    public static List<String> ownStores(int guestID) {
        User me = System.getInstance().getMember(guestID);
        if (me == null)
            return null;
        return me.storeOwned();
    }


    public static boolean usecase4_1_1_AddingProdacsToStore(String myusername, String myPassword, String storeName,
                                                            Product_boundle p) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.addProduct(storeName, p);
    }
    public static boolean usecase4_1_1_AddingProdacsToStore(String myusername, String myPassword, String storeName,
                                                            ProductDetails p) {
        User me = System.getInstance().getMember(myusername, myPassword);
        Product_boundle PB = new Product_boundle(new Product(p),p.getAmount());
        if (me == null)
            return false;
        return me.addProduct(storeName, PB);
    }

    public static boolean usecase4_1_1_AddingProdacsToStore(int guestId, String storeName, ProductDetails p) {
        User me = System.getInstance().getMember(guestId);
        Product_boundle PB = new Product_boundle(new Product(p),p.getAmount());
        if (me == null)
            return false;
        return me.addProduct(storeName, PB);
    }

    public static boolean usecase4_1_2_RemoveItem(String myusername, String myPassword, String storeName, String prodactname) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.removeProduct(storeName, prodactname);
    }

    public static boolean usecase4_1_3_EditProduct(String myusername, String myPassword, String storeName, String prodactname,
                                                   Product newdetail) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.editProduct(storeName, prodactname, newdetail);
    }

    public static boolean usecase4_2_AddDiscount(String myusername, String myPassword, String storeName, Discount discount) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.addDiscount(discount, storeName);
    }

    public static boolean usecase4_2_RemoveDiscount(String myusername, String myPassword, String storeName, int discountID) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.removeDiscount(discountID, storeName);
    }

    public static boolean usecase4_2_AddAcquisition(String myusername, String myPassword, String storeName, Acquisition acquisition) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.addacquisition(acquisition, storeName);
    }

    public static boolean usecase4_2_RemoveAcquisition(String myusername, String myPassword, String storeName, int acquisitionID) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.removeacquisition(acquisitionID, storeName);
    }


    public static boolean usecase4_3_appointOwner(String myusername, String myPassword, String storeName, String hisusername,
                                                  String hisPassword) {
        return usecase4_3_appointOwner(myusername, myPassword, storeName, hisusername);
//		User me = System.getInstance().getMember(myusername, myPassword);
//		return me.appointOwner(storeName, hisusername, hisPassword);
    }

    public static boolean usecase4_3_appointOwner(String myusername, String myPassword, String storeName, String hisusername) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.appointOwner(storeName, hisusername);
    }

    public static boolean usecase4_3_appointOwner(int gustID, String storeName, String hisusername) {
        User me = System.getInstance().getMember(gustID);
        if (me == null)
            return false;
        return me.appointOwner(storeName, hisusername);
    }


    public static boolean usecase4_5_appointManager(String myusername, String myPassword, String storeName, String username,
                                                    String hisPassword) {
        return usecase4_5_appointManager(myusername, myPassword, storeName, username);
        //return  usecase4_5_appointManager( myusername,  myPassword,  storeName);
//		User me = System.getInstance().getMember(myusername, myPassword);
//		return me.appointManager(storeName, username, hisPassword);
    }

    public static boolean usecase4_5_appointManager(String myusername, String myPassword, String storeName, String username) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.appointManager(storeName, username);
    }

    public static boolean usecase4_5_appointManager(int gustID, String storeName, String username) {
        User me = System.getInstance().getMember(gustID);
        if (me == null)
            return false;
        return me.appointManager(storeName, username);
    }

    public static boolean usecase4_6_editMangagerPermesions(String myusername, String myPassword, String storename,
                                                            String managername, List<String> permesions) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.editManagerPermesions(storename, managername, permesions);

    }

    public static boolean usecase4_7_FireManager(String myusername, String myPassword, String storeName, String username) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.fireManager(storeName, username);
    }

    public static List<Question> usecase4_9_ViewMembersQuestions(String myusername, String myPassword, String storeName) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return null;
        List<Question> questions = new LinkedList<Question>();
        questions.addAll(me.viewQuestions(storeName));
        return questions;
    }

    public static boolean usecase4_9_RespondToQuestion(String myusername, String myPassword, String ansewr, int QustionID) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return false;
        return me.giveRespond(ansewr, QustionID);
    }

    public static List<StorePurchase> usecase4_10_ViewAcquisitionHistory(String myusername, String myPassword, String storename) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return null;
        return me.ViewAquistionHistory(storename);
    }


    public static boolean accecpt_Pending_Appointment(int gustID, String storeName, String appointe) {
        return false;
        //TODO implament
    }

    public static Collection<String> get_Pending_Appointment(int gustID, String storeName) {
        return null;
        //TODO implament
    }

}
