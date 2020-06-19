package DAL;

import Domain.info.Question;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Question_DA {
    private static SessionFactory factory;

    public Question_DA(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<Question> getAll(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Question> toReturn=new LinkedList<>();

        try {
            tx = session.beginTransaction();
            List questions = session.createQuery("FROM Question").list();
            for (Iterator iterator = questions.iterator(); iterator.hasNext();){
                Question que = (Question) iterator.next();
                toReturn.add(que);
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

    public void update(Question question){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.merge(question);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Question question){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(question);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Integer add(Question question){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer queID = null;

        try {
            tx = session.beginTransaction();
            queID = (Integer)session.save(question);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        if(queID != null){
            question.setId(queID);
        }
        return queID;
    }
}
