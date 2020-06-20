package tests.UnitTests;

import Domain.Store.Product;
import Domain.Store.Product_boundle;
import Domain.Store.Store_Inventory;
import extornal.supply.Packet_Of_Prodacts;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InventoryTest {

    Store_Inventory myInventory;
    Product_boundle P1;
    Product_boundle P2;

    @Before
    public void setup() {
        myInventory = new Store_Inventory();
        List<String> Keywords = new LinkedList<>();
        Keywords.add("aaa");
        Keywords.add("bbb");
        List<String> Category = new LinkedList<>();
        Category.add("Cats");
        Category.add("dogs");
        P1 = new Product_boundle( new Product("item 1", Category,new LinkedList<>(), 34.3, 4),4);
        P2 = new Product_boundle(new Product("item 2", new LinkedList<>(),Keywords, 12.4, 2),3);
        //Product p = new Product( "item 1", new LinkedList<>(), new LinkedList<>(),  12,  5,  store);

    }

    @Test
    public void addItem() {
        // setup();
        myInventory.recive_item(new Packet_Of_Prodacts(P1));

        assertEquals(1,myInventory.items.size());
        assertEquals(P1,myInventory.getItem(P1.item.getName()));

    }

    @Test
    public void add2Item() {
        // setup();
        myInventory.recive_item(new Packet_Of_Prodacts(P1));
        myInventory.recive_item(new Packet_Of_Prodacts(P2));

        assertEquals(2,myInventory.items.size());
        assertEquals(P1,myInventory.getItem(P1.item.getName()));
        assertEquals(P2,myInventory.getItem(P2.item.getName()));

    }
    @Test
    public void RemoveItem() {
        // setup();
        myInventory.recive_item(new Packet_Of_Prodacts(P1));
        myInventory.recive_item(new Packet_Of_Prodacts(P2));
        myInventory.removeProduct(P1.item.getName());

        assertEquals(1,myInventory.items.size());
        assertEquals(P2,myInventory.getItem(P2.item.getName()));
    }

    @Test
    public void keywordsCheck() {
        // setup();
        myInventory.recive_item(new Packet_Of_Prodacts(P1));
        myInventory.recive_item(new Packet_Of_Prodacts(P2));

        assertTrue(myInventory.findProductByKeyword("aaa").contains(P2));
    }

    @Test
    public void CategoryCheck() {
        // setup();
        myInventory.recive_item(new Packet_Of_Prodacts(P1));
        myInventory.recive_item(new Packet_Of_Prodacts(P2));

        assertTrue(myInventory.findProductByCategory("dogs").contains(P1));
    }
}

