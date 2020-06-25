package DAL;

import Domain.Store.StoreImp;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Store_DA extends DA<StoreImp> {
    public Store_DA(){
        super();
        className="StoreImp";
    }

    @Override
    public Integer add(StoreImp A) {
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
