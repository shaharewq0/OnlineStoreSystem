package extornal.payment;

import java.util.LinkedList;
import java.util.List;

public class bankAccount {

	public String name ="";
	protected double sum = 0;
	protected List<Double> transactions;
	
	public bankAccount(String name) {
		this.name = name;
		transactions = new LinkedList<Double>();
	}
	
	public bankAccount(String name,int startwith)
	{
		this.name = name;
		sum = startwith;
		transactions = new LinkedList<Double>();
	}
	
	public void getMoney(double amount) {
		sum += amount;
		transactions.add(amount);
	}
}
