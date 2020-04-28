package Domain.Store;

import java.util.LinkedList;
import java.util.List;

import Domain.Store.StoreOwner.StoreOwner;
import Domain.store_System.*;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.ProductDetails;

public class User implements IUser {
    
    private shoppingCart cart;
    
    //---- dont need to be here
    private System_Role system_role;// guest member sysmanager;
    private String address;
    private int creditCardNum;
    //TODO move this to register and call it from member system role
    private List<Store_role> store_roles;

    public User() {
        cart= new shoppingCart();
    }
    
    public User(String address,int creditCardNum){
        system_role = new Guest();
        cart= new shoppingCart();
        this.address=address;
        this.creditCardNum=creditCardNum;
        store_roles = new LinkedList<>();
    }

    public boolean register(String id, String password){
        if(system_role instanceof Guest){
            boolean reg = System.getInstance().register(id,password);
            if(reg){
                return true;
            }
        }
        return false;
    }

    public boolean login(String id,String password){
        if(system_role instanceof Guest){
            Registered log = System.getInstance().login(id,password);
            if(log!=null){
                system_role = new Member(log);
                return true;
            }
        }
        return false;
    }

    public StoreImp watchStoreDetails(String name){
        return System.getInstance().getStoreDetails(name);
    }

    public List<StoreImp> watchAllStores(){
        return System.getInstance().getAllStores();
    }

    public List<Product> watchProductsInStore(String name){
        return System.getInstance().getProductsFromStore(name);
    }

    public List<Product> searchProductsByName(String name){
        return System.getInstance().searchProductsByName(name);
    }

    public List<Product> searchProductsByCategory(String category){
        return System.getInstance().searchProductsByCategory(category);
    }

    public List<Product> searchProductsByKeyword(String keyword){
        return System.getInstance().searchProductsByKeyword(keyword);
    }

    public List<Product> filterByPrice(List<Product> base , int min , int max){
        return System.getInstance().filterByPrice(base,min,max);
    }
    public List<Product> filterByRating(List<Product> base , int min , int max){
        return System.getInstance().filterByRating(base,min,max);
    }

    public List<Product> filterByCategory(List<Product> base , String category){
        return System.getInstance().filterByCategory(base,category);
    }

    public List<Product> filterByStoreRating(List<Product> base , int min, int max){
        return System.getInstance().filterByStoreRating(base,min,max);
    }

    //adding a product to a basket. if the product exists add 1 to the amount of the product in the basket
    public boolean saveProductInBasket(String productName , String storeName){
        StoreImp myStore = System.getInstance().getStoreDetails(storeName);
        if(myStore==null){
            return false;
        }
        if(System.getInstance().searchProductsByName(productName).size()==0){
            return false;
        }
        List<Product> Products = System.getInstance().searchProductsByName(productName);
        if (Products == null){
            return false;
        }
        Product toSave = null;
        for(Product p : Products){
            if(p.getStore().getName().equals(storeName) && myStore.getProducts().contains(p)){
                toSave=p;
            }
        }
        if(toSave == null){
            return false;
        }
        shoppingBasket toAdd = cart.findBasket(myStore);
        if(toAdd == null){
            toAdd= new shoppingBasket(myStore);
            toAdd.addProduct(productName,1);
           // cart.addBasket(toAdd);
            return true;
        }else
        toAdd.addProduct(productName,1);
        return true;
    }

    //removing at most amount of num of a product from the basket
    public boolean deleteProductInBasket(String productName , String storeName,int num){
        StoreImp myStore = System.getInstance().getStoreDetails(storeName);
        if(myStore==null){
            return false;
        }
        List<Product> Products = System.getInstance().searchProductsByName(productName);
        Product toDelete = null;
        for(Product p : Products){
            if(p.getStore().getName().equals(storeName) & myStore.getProducts().contains(p)){
                toDelete=p;
            }
        }
        if(toDelete == null){
            return false;
        }
        shoppingBasket toRemove = cart.findBasket(myStore);
        if(toRemove == null){
            return false;
        }
        return toRemove.removeProduct(productName,num)> 0 ;
    }



    public List<ProductDetails> getProductsInCart(){
        return cart.allProductsInCart();
    }

    public boolean purchase(){
        boolean toReturn;
        if(system_role instanceof Member ){
             toReturn= System.getInstance().memberPurchase(((Member) system_role).getRegistered().getId(),cart,creditCardNum,address);
        }
        else{
            toReturn= System.getInstance().purchase(cart,creditCardNum,address);
        }
        if (toReturn){
            cart= new shoppingCart();
        }
        return toReturn;
    }

    public boolean logout(){
        if(system_role instanceof Member){
            system_role = new Guest();
            return true;
        }
        return false;
    }

    public boolean openStore(String name, String address, int rating){
        if(system_role instanceof Member){
            StoreImp s = System.getInstance().openStore(name,address,rating);
            if (s!= null){
                store_roles.add(new Creator(s));
                return true;
            }
        }
        return false;
    }

    public List<shoppingCart> watchHistory(){
        if(system_role instanceof Member) {
            return System.getInstance().orderHistory(((Member) system_role).getRegistered().getId());
        }
        return null;
    }

    @Override
    public boolean isOwner() {
        boolean ans=false;
        for (Store_role I:store_roles) {
            if(I instanceof StoreOwner)
                ans=true;
        }
        return ans;
    }


    @Override
    public boolean isManager() {
        //TODO: no manager interface for now
        return false;
    }

    @Override
    public boolean isRegistered() {
        return system_role instanceof Registered | system_role instanceof Member;
    }
}
