package Domain.Store.workers.appoints;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
import Domain.Store.StoreImp;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.Store.workers.Store_role;
import Domain.store_System.Roles.Registered;

import java.util.HashMap;
import java.util.Map;

public class    Pending_appoint_Owner {


    public StoreOwner_Imp grantor;
    public Registered grantee;
    public StoreImp store;
    public Map<StoreOwner_Imp, Boolean> confirmstion_table = new HashMap<>();

    public Pending_appoint_Owner(StoreOwner_Imp grantor, Registered grantee, StoreImp store) {
        this.grantor = grantor;
        this.grantee = grantee;
        this.store = store;
        for (StoreOwner_Imp owner : store.getOwners()) {
            confirmstion_table.put(owner, false);
            owner.needconfirmstion(this);
        }
        confirmstion_table.put(store.getCreator(), false);
        store.getCreator().needconfirmstion(this);
    }

    public Boolean Accept(StoreOwner_Imp role) {
        if (!confirmstion_table.containsKey(role))
            return false;

        boolean confirm = confirmstion_table.replace(role, true);
        if(agreed_to_appoint()) {
            finalise_appoint();
            return true;
        }
        return !confirm;
    }

    private void finalise_appoint() {
        StoreOwner_Imp newRole = new StoreOwner_Imp(grantor, grantee);
        if (grantee.appointAsOwner(newRole)) {
            store.appointOwner(newRole.myJob);
            grantor.finalise_appoint(this);
            EventLogger.GetInstance().Add_Log(this.toString() + "pending appoint new Owner");
        } else
            ErrorLogger.GetInstance().Add_Log(this.toString() + "Owner Failed to appoint new Owner");

        //TODO delete this pending class instant
    }

    @Override
    public String toString() {
        String output = "pending appoint for store:" + store.getName() + "\n"
                + "granted by :" + grantor.getName() + "\n"
                + "granted to :" + grantee.getId() + "\n";
        for (Store_role role : confirmstion_table.keySet()) {
            output += role.getName() + " accepted: " + confirmstion_table.get(role).toString() + "\n";
        }
        return output;
    }

    public Boolean agreed_to_appoint() {
        boolean agree = true;
        for (Boolean b : confirmstion_table.values()) {
            agree = agree & b;
        }
        return agree;
    }

}
