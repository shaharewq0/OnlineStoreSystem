package Service_Layer.manager_accese;

import Domain.UserClasses.User;
import Domain.Store.StorePurchase;
import Domain.info.Question;
import Domain.UserClasses.System;

import java.util.LinkedList;
import java.util.List;

public class manager_accese {

	public static List<Question> usecase4_9_ViewMembersQuestions(String myusername, String myPassword, String storeName) {
		User me = System.getInstance().getMember(myusername, myPassword);
		if(me==null)
			return null;
		List<Question> questions = new LinkedList<Question>();
		questions.addAll(me.viewQuestions(storeName));
		return questions;
	}

	public static boolean usecase4_9_RespondToQuestion(String myusername, String myPassword, String ansewr, int QustionID) {
		User me = System.getInstance().getMember(myusername, myPassword);
		if(me==null)
			return false;
		return me.giveRespond(ansewr, QustionID);
	}

	public static List<StorePurchase> usecase4_10_ViewAcquisitionHistory(String myusername, String myPassword, String storename) {
		User me = System.getInstance().getMember(myusername, myPassword);
		if(me==null)
			return null;
		return me.ViewAquistionHistory(storename);
	}
}
