package DAL;

import Domain.UserClasses.UserPurchase;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserPurchase_DA extends DA<UserPurchase> {
    public UserPurchase_DA(){
        super();
        className="UserPurchase";
    }
    @Override
    public Integer add(UserPurchase A) {
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
