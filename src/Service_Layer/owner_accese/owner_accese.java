package Service_Layer.owner_accese;

import java.util.List;

import Domain.info.Question;
import Domain.info.Transactions;
import tests.AcceptanceTests.auxiliary.ProductDetails;

public class owner_accese {

	public boolean usecase4_1_1_AddingProdacsToStore(String prodacname) {
		//TODO need to change use case
		return false;
	}

	public boolean usecase4_1_2_RemoveItem(ProductDetails prodact) {
		return false;
	}

	public boolean usecase4_1_3_EditProduct(String prodactname,ProductDetails newdetail) {
		return false;
	}

	public boolean usecase4_3_appointOwner(String myusername,String myPassword,String username) {
		return false;
	}

	public boolean usecase4_5_appointManager(String myusername,String myPassword,String username) {
		return false;
	}

	public boolean usecase4_6_editMangagerPermesions(String myusername,String myPassword,String managername, List<String> permesions) {
		return false;
	}

	public boolean usecase4_7_FireManager(String myusername,String myPassword,String username) {
		return false;
	}

	public List<Question> usecase4_9_ViewMembersQuestions(String myusername,String myPassword){
		return null;
	}
	
	public boolean usecase4_9_RespondToQuestion(String myusername,String myPassword,String ansewr)
	{
		return false;
		
	}
	
	public List<Transactions> usecase4_10_ViewAcquisitionHistory(String myusername,String myPassword) {
		return null;
	}
}
