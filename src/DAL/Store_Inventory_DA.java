package DAL;

import Domain.Store.Product;
import Domain.Store.Store_Inventory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Store_Inventory_DA {
    private static SessionFactory factory;
    public Store_Inventory_DA(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<Store_Inventory> getAll(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Store_Inventory> toReturn=new LinkedList<>();

        try {
            tx = session.beginTransaction();
            List store_inventories = session.createQuery("FROM Store_Inventory").list();
            for (Iterator iterator = store_inventories.iterator(); iterator.hasNext();){
                Store_Inventory si = (Store_Inventory) iterator.next();
                toReturn.add(si);
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

    public void update(Store_Inventory si){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.merge(si);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Store_Inventory si){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(si);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public Integer add(Store_Inventory si){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer siID = null;

        try {
            tx = session.beginTransaction();
            siID = (Integer) session.save(si);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        if(siID != null){
            si.setId(siID);
        }
        return siID;
    }
}
