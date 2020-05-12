package Domain.Store.workers;

import java.util.List;

import Domain.RedClasses.IUser;
import Domain.Store.Product;
import Domain.Store.Purchase;
import Domain.Store.StoreImp;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeItem(Product item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean appointOwner(IUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean appointManager(IUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> void setPremissions(IUser manager, List<T> Permissions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean fire(IUser manager) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean editItem(String OLD_item, Product NEW_item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeItem(String prodactname) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Purchase> getPurchaseHistory() {
		// TODO Auto-generated method stub
		return null;
	}
}
