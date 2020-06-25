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
    product=new Product("shahar",category,keyWords,4.5,2);
    }

    @Test
    public void getAll() {
        int size=da.getAll().size();
        da.add(product);
        int Nsise=da.getAll().size();
        assertTrue((Nsise-1)==size);
    }

    @Test
    public void update() {
        da.add(product);
        //updating product categories
        List<String> categories=new LinkedList<>();
        categories.add("dary");
        product.setCategory(categories);
        da.update(product);
        assertTrue(da.getAll().get(0).getCategory().get(0).equals("dary"));

        //updating keywords
        product.setKeyWords(categories);
        da.update(product);
        assertTrue(da.getAll().get(0).getKeyWords().get(0).equals("dary"));

        product.setName("new name");
        product.setPrice(100.01);
        product.setRating(0);

        da.update(product);

        assertTrue(da.getAll().get(0).getName().equals("new name"));
        assertTrue(da.getAll().get(0).getPrice()==100.01);
        assertTrue(da.getAll().get(0).getRating()==0);
        assertTrue(da.getAll().size()==1);
    }

    @Test
    public void delete() {
        da.add(product);
        int size=da.getAll().size();
        da.delete(product);
        int Nsize=da.getAll().size();
        assertTrue((Nsize+1)==size);
    }

}