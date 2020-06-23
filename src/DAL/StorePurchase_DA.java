package DAL;

import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StorePurchase_DA extends DA<StorePurchase> {

    public StorePurchase_DA(){
        super();
        className="StorePurchase";
    }

    @Override
    public Integer add(StorePurchase A) {
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
