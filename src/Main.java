import Domain.Store.Product;
import Domain.info.ProductDetails;
import Service_Layer.guest_accese.guest_accese;
import Service_Layer.owner_accese.owner_accese;

import java.util.LinkedList;
import java.util.List;

public class Main {
	
	
	
    public static void main(String[] args){
        List<String> lst = new LinkedList<>();
        lst.add("fruits");

        if(guest_accese.usecase2_2_guest_register("k", "")){
            System.out.println("good");
        }
        else {
            System.out.println("bad");
        }


        if(owner_accese.usecase4_1_1_AddingProdacsToStore("k", "", "ebay", (new ProductDetails("banana", lst, "ebay", 300, 20.15)))){
            System.out.println("good");
        }
        else {
            System.out.println("bad");
        }
    }
}
