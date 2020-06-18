package DAL;

import Domain.Store.Product;
import org.junit.Before;
import org.junit.Test;


import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class Product_DATest {

    Product_DA da;
    Product product;
    LinkedList<String> category;
    LinkedList<String> keyWords;

    @Before
    public void setUp() throws Exception {
    da=new Product_DA();
    category=new LinkedList<>();
    keyWords=new LinkedList<>();

    category.add("milk");
    keyWords.add("key");
    Product product=new Product("shahar",category,keyWords,4.5,2);
    }

    @Test
    public void getAll() {
        List<Product> t=da.getAll();

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void add() {
    }
}