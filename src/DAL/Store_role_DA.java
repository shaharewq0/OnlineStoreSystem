package DAL;

import Domain.Store.workers.Store_role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Store_role_DA extends DA<Store_role> {
    public Store_role_DA(){
        super();
        className="Store_role";
    }
    @Override
    public Integer add(Store_role A) {
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
        if(ID!=null){
            A.setId(ID);
        }
        return ID;
    }
}
