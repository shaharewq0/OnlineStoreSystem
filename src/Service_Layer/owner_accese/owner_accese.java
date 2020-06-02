package Service_Layer.owner_accese;

import Domain.RedClasses.User;
import Domain.Store.Product;
import Domain.Store.StorePurchase;
import Domain.info.Question;
import Domain.store_System.System;

import java.util.LinkedList;
import java.util.List;

public class owner_accese {

	public static List<String> ownStores(int guestID){
		User me = System.getInstance().getMember(guestID);
		return me.storeOwned();
	}




	public static boolean usecase4_1_1_AddingProdacsToStore(String myusername, String myPassword, String storeName,
			Product p) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.addProduct(storeName, p);
	}

	public static boolean usecase4_1_2_RemoveItem(String myusername, String myPassword, String storeName, String prodactname) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.removeProduct(storeName, prodactname);
	}

	public static boolean usecase4_1_3_EditProduct(String myusername, String myPassword, String storeName, String prodactname,
			Product newdetail) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.editProduct(storeName, prodactname, newdetail);
	}

	public static boolean usecase4_3_appointOwner(String myusername, String myPassword, String storeName, String hisusername,
			String hisPassword) {
		return usecase4_3_appointOwner( myusername,  myPassword,  storeName,  hisusername);
//		User me = System.getInstance().getMember(myusername, myPassword);
//		return me.appointOwner(storeName, hisusername, hisPassword);
	}
	public static boolean usecase4_3_appointOwner(String myusername, String myPassword, String storeName, String hisusername) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.appointOwner(storeName, hisusername);
	}

	public static boolean usecase4_3_appointOwner(int gustID, String storeName, String hisusername) {
		User me = System.getInstance().getMember(gustID);
		return me.appointOwner(storeName, hisusername);
	}


	public static boolean usecase4_5_appointManager(String myusername, String myPassword, String storeName, String username,
			String hisPassword) {
		return  usecase4_5_appointManager(myusername,myPassword,storeName,username);
		//return  usecase4_5_appointManager( myusername,  myPassword,  storeName);
//		User me = System.getInstance().getMember(myusername, myPassword);
//		return me.appointManager(storeName, username, hisPassword);
	}

	public static boolean usecase4_5_appointManager(String myusername, String myPassword, String storeName, String username) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.appointManager(storeName, username);
	}

	public static boolean usecase4_5_appointManager(int guestID, String storeName, String username) {
		User me = System.getInstance().getMember(guestID);
		return me.appointManager(storeName, username);
	}



	public static boolean usecase4_6_editMangagerPermesions(String myusername, String myPassword, String storename,
			String managername, List<String> permesions) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.editManagerPermesions(storename, managername, permesions);

	}



	public static boolean usecase4_7_FireManager(String myusername, String myPassword, String storeName, String username) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.fireManager(storeName, username);
	}

	public static List<Question> usecase4_9_ViewMembersQuestions(String myusername, String myPassword, String storeName) {
		User me = System.getInstance().getMember(myusername, myPassword);
		List<Question> questions = new LinkedList<Question>();
		questions.addAll(me.viewQuestions(storeName));
		return questions;
	}

	public static boolean usecase4_9_RespondToQuestion(String myusername, String myPassword, String ansewr, int QustionID) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.giveRespond(ansewr, QustionID);
	}

	public static List<StorePurchase> usecase4_10_ViewAcquisitionHistory(String myusername, String myPassword, String storename) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.ViewAquistionHistory(storename);
	}

}
