package Domain.Store;

import java.util.Collection;
import java.util.List;

public class Discount {

	
	static public Discount Copy(Discount other)
	{
		return new Discount();
	}

	public double implamentDis(double add) {
		return add;
	}

	
	public static Collection<Discount> Copy(List<Discount> discounts) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
