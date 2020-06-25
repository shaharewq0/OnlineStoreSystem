package DAL;

import Domain.UserClasses.User_Purchase_History;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserPurchaseHistory_DA extends DA<User_Purchase_History> {
    public UserPurchaseHistory_DA(){
        super();
        className="User_Purchase_History";
    }
    @Override
    public Integer add(User_Purchase_History A) {
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
