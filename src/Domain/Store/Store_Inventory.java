package Domain.Store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.info.ProductDetails;
import extornal.supply.Packet_Of_Prodacts;
import extornal.supply.inventory;

public class Store_Inventory implements inventory {

    public Map<String, Product_boundle> items = new HashMap<String, Product_boundle>();

    @Override
    public boolean recive_item(Packet_Of_Prodacts pack) {
        if (pack == null || pack.items == null || pack.items.isEmpty())
            return false;

        for (Product_boundle p : pack.items) {
            if (items.containsKey(p.item.getName()))
                items.get(p.item.getName()).add(p.size());
            else
                items.put(p.item.getName(), p);
        }
        return true && checkConstrains_hase1Ofitem();

    }

    public boolean removeProduct(String productname) {
        if (!items.containsKey(productname))
            return false;
        items.remove(productname);
        return true && checkConstrains_hase1Ofitem();
    }

    public boolean editProduct(String old_p, Product new_p) {
        if (items.containsKey(old_p)) {
            items.get(old_p).item = new_p;
            // items.replace(old_p, new_p);
            return true && checkConstrains_hase1Ofitem();
        }
        return false;
    }

    public List<Product> findProductByCategory(String category) {
        List<Product> toReturn = new LinkedList<>();
        for (Product_boundle pb : items.values()) {
            if (pb != null && pb.item.getCategory().contains(category)) {
                toReturn.add(pb.item);
            }
        }
        return toReturn;
    }

    public List<Product_boundle> findProduct_BundleByCategory(String category) {
        List<Product_boundle> toReturn = new LinkedList<>();
        for (Product_boundle pb : items.values()) {
            if (pb != null && pb.item.getCategory().contains(category)) {
                toReturn.add(pb);
            }
        }
        return toReturn;
    }

    public List<Product> findProductByKeyword(String keyword) {
        List<Product> toReturn = new LinkedList<>();
        for (Product_boundle pb : items.values()) {
            if (pb != null && pb.item.getKeyWords().contains(keyword)) {
                toReturn.add(pb.item);
            }
        }
        return toReturn;
    }
    public List<Product_boundle> findProduct_BundleByKeyword(String keyword) {
        List<Product_boundle> toReturn = new LinkedList<>();
        for (Product_boundle pb : items.values()) {
            if (pb != null && pb.item.getKeyWords().contains(keyword)) {
                toReturn.add(pb);
            }
        }
        return toReturn;
    }


    public boolean recive_item(ProductDetails p) {
        if (items.containsKey(p.getName())) {
            items.get(p.getName()).add(p.getAmount());
        } else
            items.put(p.getName(), new Product_boundle(new Product(p), p.getAmount()));
        return true && checkConstrains_hase1Ofitem();
    }

    public ProductDetails findProductDetailsByName(String name, String storeName) {
        //TODO change this
        if (items.containsKey(name))
            return new ProductDetails(items.get(name).item, items.get(name).size(), storeName);
        return null;
    }

    public Product getItem(String name) {
        return items.get(name).item;
    }

    public Product_boundle getBundleItem(String name) {return items.get(name);
    }

    public boolean checkConstrains_hase1Ofitem() {

        for (Product_boundle P : items.values()) {
            if (P.size() < 0)
                return false;
        }
        return true;
    }


}
