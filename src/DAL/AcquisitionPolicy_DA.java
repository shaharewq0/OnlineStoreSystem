package DAL;

import Domain.Policies.Acquisitions.AcquisitionPolicy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AcquisitionPolicy_DA {
    private static SessionFactory factory;
    public AcquisitionPolicy_DA(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<AcquisitionPolicy> getAll(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<AcquisitionPolicy> toReturn=new LinkedList<>();

        try {
            tx = session.beginTransaction();
            List list = session.createQuery("FROM AcquisitionPolicy").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();){
                AcquisitionPolicy A = (AcquisitionPolicy) iterator.next();
                toReturn.add(A);
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

    public void update(AcquisitionPolicy A){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.merge(A);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(AcquisitionPolicy A){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(A);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public Integer add(AcquisitionPolicy A){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer ID = null;

        try {
            tx = session.beginTransaction();
            ID = (Integer) session.save(A);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        if(ID != null){
            A.setId(ID);
        }
        return ID;
    }
}
