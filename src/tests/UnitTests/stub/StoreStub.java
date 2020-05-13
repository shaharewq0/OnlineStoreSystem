package tests.UnitTests.stub;

import java.util.List;

import Domain.RedClasses.IUser;
import Domain.Store.Discount;
import Domain.Store.IStore;
import Domain.Store.Product;
import Domain.Store.Purchase;
import Domain.Store.StorePurchase;
import Domain.Store.workers.StoreManager_Imp;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.info.ProductDetails;

public class StoreStub implements IStore {
    UserStub Owner;
    UserStub manager;

    public void setOwner(UserStub owner) {
        Owner = owner;
    }

    public void setManager(UserStub manager) {
        this.manager = manager;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public boolean addProduct(Product p) {
        return false;
    }

    @Override
    public Product findProductByName(String name) {
        return null;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public List<Product> findProductByKeyword(String keyword) {
        return null;
    }

    @Override
    public int getRating() {
        return 0;
    }

 


    
    @Override
    public List<StorePurchase> viewPurchaseHistory() {
        return null;
    }

	@Override
	public Boolean CheckItemAvailable(ProductDetails items) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPrice(String item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product TakeItem(String name, int amout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Discount> getDiscounts(String name) {
		//TODO imp
		return null;
	}

	@Override
	public boolean removeProduct(String pName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editProduct(String OLD_p, Product NEW_p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean appointManager(StoreManager_Imp user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean appointOwner(StoreOwner_Imp user) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean fireManager(String user) {
		// TODO Auto-generated method stub
		return false;
	}
}
