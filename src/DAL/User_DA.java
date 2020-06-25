package DAL;

import Domain.UserClasses.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class User_DA extends DA<User> {
    public User_DA(){
        super();
        className="User";
    }
    @Override
    public Integer add(User A) {
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
