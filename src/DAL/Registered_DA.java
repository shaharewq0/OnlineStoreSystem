package DAL;

import Domain.store_System.Roles.Registered;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Registered_DA extends DA<Registered> {

    public Registered_DA(){
        super();
        className="Registered";
    }

    @Override
    public Integer add(Registered A) {
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
        return ID;
    }
}
