package DAL;

import Domain.UserClasses.shoppingBasket;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Basket_DA extends DA<shoppingBasket> {
    public Basket_DA(){
        super();
        className="shoppingBasket";
    }
    @Override
    public Integer add(shoppingBasket A) {
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
