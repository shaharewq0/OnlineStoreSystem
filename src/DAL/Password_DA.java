package DAL;

import extornal.Security.Password;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Password_DA {
    private static SessionFactory factory;

    public Password_DA(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Integer addPassword(Password pass){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer passID = null;

        try {
            tx = session.beginTransaction();
            passID = (Integer) session.save(pass);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        if(passID != null){
            pass.setId(passID);
        }
        return passID;
    }

    public List<Password> getAllPassword(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Password> toReturn=new LinkedList<>();

        try {
            tx = session.beginTransaction();
            List passwords = session.createQuery("FROM Password").list();
            for (Iterator iterator = passwords.iterator(); iterator.hasNext();){
                Password pass = (Password) iterator.next();
                toReturn.add(pass);
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

    // to use this, send an updated object;
    // i assume the object is in the table
    public void updatePassword(Password pass){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(pass);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void deletePassword(Password pass){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(pass);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
