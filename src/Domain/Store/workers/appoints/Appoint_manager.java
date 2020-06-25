package Domain.Store.workers.appoints;

import Domain.Store.StoreImp;
import Domain.Store.workers.StoreManager_Imp;
import Domain.Store.workers.Store_role;

public class Appoint_manager {
	private int id;
	public Store_role grantor;
	public StoreManager_Imp grantee;
	public StoreImp store;

	public Appoint_manager(){}

	public int getId() {
		return id;
	}

	public StoreImp getStore() {
		return store;
	}

	public Store_role getGrantor() {
		return grantor;
	}

	public StoreManager_Imp getGrantee() {
		return grantee;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStore(StoreImp store) {
		this.store = store;
	}

	public void setGrantor(Store_role grantor) {
		this.grantor = grantor;
	}

	public void setGrantee(StoreManager_Imp grantee) {
		this.grantee = grantee;
	}
}
