package DAL;

import Domain.store_System.Roles.System_Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class System_role_DA extends DA<System_Role> {

    public System_role_DA(){
        super();
        className="System_role";
    }

    @Override
    public Integer add(System_Role A) {
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
