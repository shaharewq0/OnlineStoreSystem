package Domain.Store.workers.appoints;

import Domain.Store.StoreImp;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.Store.workers.Store_role;

public class Appoint_Owner {
	private int id;
	public Store_role grantor;
	public StoreOwner_Imp grantee;
	public StoreImp store;

	public Appoint_Owner(){}

	public int getId() {
		return id;
	}

	public Store_role getGrantor() {
		return grantor;
	}

	public StoreOwner_Imp getGrantee() {
		return grantee;
	}

	public StoreImp getStore() {
		return store;
	}

	public void setStore(StoreImp store) {
		this.store = store;
	}

	public void setGrantee(StoreOwner_Imp grantee) {
		this.grantee = grantee;
	}

	public void setGrantor(Store_role grantor) {
		this.grantor = grantor;
	}

	public void setId(int id) {
		this.id = id;
	}
}
