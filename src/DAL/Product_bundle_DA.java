package DAL;

import Domain.Store.Product;
import Domain.Store.Product_boundle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Product_bundle_DA extends DA<Product_boundle> {
    public Product_bundle_DA(){
        super();
        className="Product_boundle";
    }

    public Integer add(Product_boundle pb){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer pbID = null;

        try {
            tx = session.beginTransaction();
            pbID = (Integer) session.save(pb);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        if(pbID != null){
            pb.setId(pbID);
        }
        return pbID;
    }
}
