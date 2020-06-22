package Domain.info;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class MangaerPermesions {

	public static final String[] permesions = { "addItem", "editItem", "removeItem", "getPurchaseHistory", "viewQuestions",
			"giveRespond", "appointOwner", "appointManager", "fire", "getfire", "editManagerPermesions" ,"addDiscount", "getDiscounts", "removeDiscount","addacquisition","removeacquisition"};

	public static final Collection<String> defult_permesions =Arrays.asList( "getPurchaseHistory", "viewQuestions", "giveRespond");

}
