package tests.UnitTests.stub;

import tests.AcceptanceTests.auxiliary.ProductDetails;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;

import java.util.LinkedList;
import java.util.List;

import Domain.Store.IStore;
import Domain.Store.IUser;
import Domain.Store.Product;

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
    public boolean removeProduct(Product p) {
        return false;
    }

    @Override
    public boolean editProduct(Product OLD_p, Product NEW_p) {
        return false;
    }

    @Override
    public boolean appointOwner(IUser user) {
        return false;
    }

    @Override
    public boolean appointManager(IUser user) {
        return false;
    }

    @Override
    public boolean fireManager(IUser user) {
        return false;
    }

    @Override
    public List<PurchaseDetails> viewPurchaseHistory() {
        List<PurchaseDetails> A=new LinkedList<>();
        A.add(new PurchaseDetails());
        return A;
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
}
