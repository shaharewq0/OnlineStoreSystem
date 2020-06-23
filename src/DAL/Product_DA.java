package DAL;

import Domain.Store.Product;
import extornal.Security.Password;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Product_DA extends DA<Product>{
    public Product_DA(){
        super();
        className="Product";
    }

    public Integer add(Product product){
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
