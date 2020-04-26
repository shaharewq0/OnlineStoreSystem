package extornal.payment;

import java.util.LinkedList;
import java.util.List;

public class bankAccount {

	public String name ="";
	protected int sum = 0;
	protected List<Integer> transactions;
	
	public bankAccount(String name) {
		this.name = name;
		transactions = new LinkedList<Integer>();
	}
	
	public bankAccount(String name,int startwith)
	{
		this.name = name;
		sum = startwith;
		transactions = new LinkedList<Integer>();
	}
	
	public void getMoney(int amount) {
		sum += amount;
		transactions.add(amount);
	}
}
