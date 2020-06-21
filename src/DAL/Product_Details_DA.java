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

public class Product_Details_DA {
    private static SessionFactory factory;
    public Product_Details_DA(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<ProductDetails> getAll(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<ProductDetails> toReturn=new LinkedList<>();

        try {
            tx = session.beginTransaction();
            List productDetails = session.createQuery("FROM ProductDetails").list();
            for (Iterator iterator = productDetails.iterator(); iterator.hasNext();){
                ProductDetails prod = (ProductDetails) iterator.next();
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

    public void update(ProductDetails product){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(ProductDetails product){
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
