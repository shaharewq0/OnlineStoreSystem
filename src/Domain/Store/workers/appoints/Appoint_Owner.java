package Domain.Store.workers.appoints;

import Domain.Store.StoreImp;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.Store.workers.Store_role;

public class Appoint_Owner {
	public Store_role grantor;
	public StoreOwner_Imp grantee;
	public StoreImp store;
}