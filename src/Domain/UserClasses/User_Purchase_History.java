package Domain.UserClasses;

import java.util.LinkedList;
import java.util.List;

public class User_Purchase_History {
	private int id;
	public List<UserPurchase> history;

	public User_Purchase_History(){
		history=new LinkedList<>();
	}

	public int getId() {
		return id;
	}

	public List<UserPurchase> getHistory() {
		return history;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHistory(List<UserPurchase> history) {
		this.history = history;
	}
}
