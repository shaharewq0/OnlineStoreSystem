package Domain.store_System.Roles;

import Domain.UserClasses.User;
import Domain.Store.StoreImp;
import Domain.info.StoreInfo;
import Domain.store_System.System;

public class Member implements System_Role {
	private User user;
	private int id;

	public Member(User user) {
		this.user = user;
	}

	public Member(){}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public StoreImp OpenStore(StoreInfo store) {
		return System.getInstance().openStore(store);
	}
}
