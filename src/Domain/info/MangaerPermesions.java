package Domain.info;

import java.util.Arrays;
import java.util.Collection;

public class MangaerPermesions {

	public static final String[] permesions = { "addItem", "editItem", "removeItem", "getPurchaseHistory", "viewQuestions",
			"giveRespond", "appointOwner", "appointManager", "fire", "getfire", "editManagerPermesions" };

	public static final Collection<String> defult_permesions =Arrays.asList( "getPurchaseHistory", "viewQuestions", "giveRespond");

}
