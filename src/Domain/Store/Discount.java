package Domain.Store;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

// TODO: REMOVE !!!

public class Discount {

	
	static public Discount Copy(Discount other)
	{
		return new Discount();
	}

	public double implamentDis(double add) {
		return add;
	}

	
	public static Collection<Discount> Copy(List<Discount> discounts) {
		List<Discount> lst = new LinkedList<>();

		for (Discount d: discounts) {
			lst.add(Copy(d));
		}

		return lst;
	}


	
}
