package Domain.Store.workers;

import java.util.HashMap;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
import Domain.Store.StoreImp;
import Domain.store_System.Roles.Registered;

public class Creator extends StoreOwner_Imp {

	public Creator(StoreImp store, Registered myname) {
		super();

		user = myname;

		myJob.store = store;

		workername = "the boss";
		OwnerAppointeis = new HashMap<String, Store_role>();
		ManagerAppointeis = new HashMap<String, Store_role>();
		EventLogger.GetInstance().Add_Log(this.toString() + "- Created StoreOwner");
		//danger
		if(store != null)
			store.myCreator(this);
	}

	@Override
	public boolean canPromoteToOwner() {
		ErrorLogger.GetInstance().Add_Log(this.toString() + "- someone want to promot the boss");
		return false;
	}

	@Override
	public boolean getfire() {
		ErrorLogger.GetInstance().Add_Log(this.toString() + "- someone want to fire the boss");
		return false;
	}

	@Override
	public String getType(){
		return "creator";
	}

}
