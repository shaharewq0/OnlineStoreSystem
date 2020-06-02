package Domain.RedClasses;


import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;
import Domain.store_System.System;

import java.util.*;

public class shoppingCart implements IshoppingCart {
    Map<String,shoppingBasket> baskets;

    public shoppingCart(){
        baskets=new HashMap<>();
    }

    public Collection<shoppingBasket> getBaskets() {
        return baskets.values();
    }

    public List<ProductDetails> allProductsInCart(){
       List<ProductDetails> output = new LinkedList<ProductDetails>();
       for (shoppingBasket basket: baskets.values()) {
    	   output.addAll(basket.getProducts());
       }
       return output;
    }

    public shoppingBasket findBasket(String name){
    	if(baskets.containsKey(name))
    		return baskets.get(name);
    	//else
    	baskets.put(name, new shoppingBasket(System.getInstance().getStoreDetails(name)));
    	return baskets.get(name);

    }
    
	public int removeItem(String Storename,String Itemname,int amount) {
    	IshoppingBasket basket = baskets.get(Storename);
    	if (basket == null)
    		return 0;
    	return basket.removeProduct(Itemname, amount);
	}
    
    @Override
	public double CalcPrice() {
		double cost= 0;
		for (shoppingBasket basket : baskets.values()) {
			cost += basket.CalcPrice();
		}
		return cost;
	}

	@Override
	public List<Product> getItems() {
		List<Product> takeout = new LinkedList<Product>();
		for (shoppingBasket basket  : baskets.values()) {
			takeout.addAll(basket.getItems());
		}
		return takeout;
	}

	public UserPurchase Complet_Purchase() {
		UserPurchase p = new UserPurchase();
		for (shoppingBasket basket : baskets.values()) {
			 p.eachPurchase.add(basket.Complet_Purchase());
		}
		return p;
	}
}
