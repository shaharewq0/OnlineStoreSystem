package DAL;

import Domain.Store.Product;
import Domain.Store.Store_Inventory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Store_Inventory_DA extends DA<Store_Inventory> {

    public Store_Inventory_DA(){
        super();
        className="Store_Inventory";
    }

    public Integer add(Store_Inventory si){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer siID = null;

        try {
            tx = session.beginTransaction();
            siID = (Integer) session.save(si);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        if(siID != null){
            si.setId(siID);
        }
        return siID;
    }
}
