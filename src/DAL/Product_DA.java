package DAL;

import Data_access.DOA;
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

public class Product_DA{
    private static SessionFactory factory;
    public Product_DA(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<Product> getAll(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Product> toReturn=new LinkedList<>();

        try {
            tx = session.beginTransaction();
            List products = session.createQuery("FROM Product").list();
            for (Iterator iterator = products.iterator(); iterator.hasNext();){
                Product prod = (Product) iterator.next();
                toReturn.add(prod);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return toReturn;
    }

    public void update(Product product){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Product product){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
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
