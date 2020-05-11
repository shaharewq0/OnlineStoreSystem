package Domain.Store.workers;

import java.util.List;

import Domain.RedClasses.IUser;
import Domain.RedClasses.User;
import Domain.Store.Product;
import Domain.Store.Purchase;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;

public class Creator implements Store_role {
    private StoreImp store;

    public Creator(StoreImp store) {
        this.store = store;
    }

    public StoreImp getStore() {
        return store;
    }

	@Override
	public boolean addItem(Product item) {
		return store.addProduct(item);
		//return false;
	}
    
	@Override
	public boolean addItem(ProductDetails item) {
		return store.addProduct(item);
		//return false;
	}

//	@Override
//	public boolean removeItem(Product item) {
//		// TODO Auto-generated method stub
//		return false;
//	}


	@Override
	public boolean appointOwner(IUser user) {
		return user.appointAsOwner(this, store.getName());
	}

	@Override
	public boolean appointManager(IUser user) {
		return user.appointAsManager(this, store.getName());
	}

//	@Override
//	public <T> void setPremissions(IUser manager, List<T> Permissions) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public boolean fire(IUser manager) {
		return manager.getFired(store.getName());
		//return false;
	}

	

	@Override
	public boolean editItem(String OLD_item, Product NEW_item) {
		return store.editProduct(OLD_item, NEW_item);
	}

	@Override
	public boolean removeItem(String prodactname) {
		return store.removeProduct(prodactname);
	}

	@Override
	public List<Purchase> getPurchaseHistory() {
		return store.getPurchaseHistory();

	}

	@Override
	public boolean editManagerPermesions(String managername, List<String> permesions) {
		
		return store.editManagerPermesions(managername,permesions);
	}

	@Override
	public List<Question> viewQuestions() {
		return store.getQuestions();
	}

	@Override
	public boolean giveRespond(String ansewr, int qustionID) {
		
		return store.respondToQuestion(ansewr,qustionID);
	}

	


	@Override
	public boolean canPromoteToOwner() {
		return false;
	}


}
