package Service_Layer.manager_accese;

import Domain.RedClasses.User;
import Domain.Store.Purchase;
import Domain.Store.StorePurchase;
import Domain.info.Question;
import Domain.store_System.System;

import java.util.LinkedList;
import java.util.List;

public class manager_accese {

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

	public static List<Purchase> usecase4_10_ViewAcquisitionHistory(String myusername, String myPassword, String storename) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.ViewAquistionHistory(storename);
	}
}
