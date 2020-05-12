package Domain.RedClasses;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;
import Domain.store_System.System;

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


    public shoppingBasket findBasket(StoreImp s){
        for(shoppingBasket basket : getBaskets()){
            if(basket.getStore().getName().equals(s.getName())){
                return basket;
            }
        }
        return null;
    }

    public shoppingBasket findBasket(String name){
    	if(baskets.containsKey(name))
    		return baskets.get(name);
    	//else
    	baskets.put(name, new shoppingBasket(System.getInstance().getStoreDetails(name)));
    	return baskets.get(name);

    }
    
	public int removeItem(String Storename,String Itemname,int amount) {
		return baskets.get(Storename).removeProduct(Itemname, amount);
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
