package Domain.RedClasses;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.Store.Product;
import Domain.Store.StoreImp;
import tests.AcceptanceTests.auxiliary.ProductDetails;

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

    // connect 2 lists
//    private void concat(List<MyPair<Product,Integer>> a , List<MyPair<Product,Integer>> b){
//        for(MyPair<Product,Integer> p : b){
//            if(!a.contains(p)){
//                a.add(p);
//            }
//        }
//    }

//    public boolean addBasket(shoppingBasket b){
//        if(!baskets.contains(b)){
//            baskets.add(b);
//            return true;
//        }
//        return false;
//    }

    public shoppingBasket findBasket(StoreImp s){
        for(shoppingBasket basket : getBaskets()){
            if(basket.getStore().getName().equals(s.getName())){
                return basket;
            }
        }
        return null;
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
}
