package DAL;

import Domain.Store.Product;
import org.junit.Before;
import org.junit.Test;
import Domain.Store.concreate_Product;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class Product_DATest {

    Product_DA da;
    Product product;
    LinkedList<String> category;
    LinkedList<String> keyWords;
    concreate_Product concreate_product;
    @Before
    public void setUp() throws Exception {
    da=new Product_DA();
    category=new LinkedList<>();
    keyWords=new LinkedList<>();
    concreate_product=new concreate_Product();
    category.add("milk");
    keyWords.add("key");
    Product product=new Product("shahar",category,keyWords,2.3,4,"ok");
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