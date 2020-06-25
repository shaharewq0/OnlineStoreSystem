package DAL;

import Domain.info.ProductDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Product_Details_DA extends DA<ProductDetails>{

    public Product_Details_DA(){
       super();
       className="ProductDetails";
    }

    public Integer add(ProductDetails product){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer prodID = null;

        try {
            tx = session.beginTransaction();
            prodID = (Integer) session.save(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        if(prodID != null){
            product.setId(prodID);
        }
        return prodID;
    }
}
