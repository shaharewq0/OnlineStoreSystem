package tests.UnitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import Domain.Store.StoreImp;

public class StoreOwner_ImpTest {

	StoreImp storeOwner = null;// new StoreOwner_Imp(new StoreStub());

//	@org.junit.Test
//	public void appointOwner() {
//		UserStub userStub = new UserStub();
//		assertTrue(storeOwner.appointOwner(userStub));
//		StoreStub aStore = new StoreStub();
//		aStore.setOwner(userStub);
//		userStub.setOwner(true);
//		assertFalse(storeOwner.appointOwner(userStub));
//	}
//
//	@org.junit.Test
//	public void appointManager() {
//		UserStub userStub = new UserStub();
//		assertTrue(storeOwner.appointManager(userStub));
//		userStub.setManager(true);
//		assertFalse(storeOwner.appointManager(userStub));
//		userStub.setManager(false);
//		userStub.setOwner(true);
//		assertFalse(storeOwner.appointManager(userStub));
//	}
//
//	@org.junit.Test
//	public void fire() {
//		StoreStub A = new StoreStub();
//		StoreOwner owner = null;// new StoreOwner_Imp(A);
//		UserStub managerForA = new UserStub();
//		owner.appointManager(managerForA);
//		managerForA.setManager(true);
//		assertFalse(storeOwner.fire(managerForA));
//		UserStub managerUnderOurStore = new UserStub();
//		storeOwner.appointManager(managerUnderOurStore);
//		managerUnderOurStore.setManager(true);
//		assertTrue(storeOwner.fire(managerUnderOurStore));
//	}

	@org.junit.Test
	public void viewPurchaseHistory() {
		// assertEquals("AN ITEM",storeOwner.viewPurchaseHistory().get(0));
	}
}