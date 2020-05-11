package Domain.Store.workers;

import java.util.HashMap;

import Domain.Store.StoreImp;

public class Creator extends StoreOwner_Imp {

	public Creator(StoreImp store) {
		super();
		this.store = store;
		workername = "the boss";
		OwnerAppointeis = new HashMap<String, Store_role>();
		ManagerAppointeis = new HashMap<String, Store_role>();
	}

	@Override
	public boolean canPromoteToOwner() {
		return false;
	}

	@Override
	public boolean getfire() {
		return false;
	}

}
