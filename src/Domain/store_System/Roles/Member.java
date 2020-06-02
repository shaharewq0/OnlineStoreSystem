package Domain.store_System.Roles;

import Domain.UserClasses.User;
import Domain.Store.StoreImp;
import Domain.info.StoreInfo;
import Domain.UserClasses.System;

public class Member implements System_Role {
	// private Registered registered;
	private User user;
//    public Member(Registered registered) {
//        this.registered = registered;
//    }

	public Member(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
//	public Registered getRegistered() {
//        return registered;
//    }

	public StoreImp OpenStore(StoreInfo store) {
		return System.getInstance().openStore(store);
	}
}
