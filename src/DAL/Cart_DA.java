package DAL;

import Domain.UserClasses.shoppingCart;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Cart_DA extends DA<shoppingCart>{

    public Cart_DA(){
        super();
        className="shoppingCart";
    }

    @Override
    public Integer add(shoppingCart A) {
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
