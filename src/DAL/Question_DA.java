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

public class Question_DA extends DA<Question>{

    public Question_DA(){
        super();
        className="Question";
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
