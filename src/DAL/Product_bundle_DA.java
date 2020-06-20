package DAL;

import Domain.Store.Product;
import Domain.Store.Product_boundle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Product_bundle_DA {
    private static SessionFactory factory;
    public Product_bundle_DA(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<Product_boundle> getAll(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Product_boundle> toReturn=new LinkedList<>();

        try {
            tx = session.beginTransaction();
            List product_bundles = session.createQuery("FROM Product_boundle").list();
            for (Iterator iterator = product_bundles.iterator(); iterator.hasNext();){
                Product_boundle pb = (Product_boundle) iterator.next();
                toReturn.add(pb);
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

    public void update(Product_boundle pb){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.merge(pb);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Product_boundle pb){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(pb);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public Integer add(Product_boundle pb){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer pbID = null;

        try {
            tx = session.beginTransaction();
            pbID = (Integer) session.save(pb);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        if(pbID != null){
            pb.setId(pbID);
        }
        return pbID;
    }
}
