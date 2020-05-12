package Service_Layer.manager_accese;

import java.util.List;

import Domain.RedClasses.User;
import Domain.Store.Purchase;
import Domain.info.Question;
import Domain.store_System.System;

public class manager_accese {
	

	public List<Question> usecase4_9_ViewMembersQuestions(String myusername, String myPassword, String storeName) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.viewQuestions(storeName);
	}
	
	public boolean usecase4_9_RespondToQuestion(String myusername, String myPassword, String ansewr, int QustionID) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.giveRespond(ansewr, QustionID);
	}
	
	public List<Purchase> usecase4_10_ViewAcquisitionHistory(String myusername, String myPassword,String storename) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.ViewAquistionHistory(storename);
	}
}
