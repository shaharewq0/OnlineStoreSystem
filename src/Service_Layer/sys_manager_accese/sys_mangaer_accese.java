package Service_Layer.sys_manager_accese;

import Domain.UserClasses.User;
import Domain.UserClasses.UserPurchase;
import Domain.Store.StorePurchase;
import Domain.store_System.System;

import java.util.List;

public class sys_mangaer_accese {

    private static String prev_state = "";

    public static boolean InitSystem(String myusername, String MyPassword) {
        System.getInstance().init(myusername, MyPassword);
        return true;

    }
//update
    public static List<UserPurchase> usecase6_4A_WatchPurchesHistoryofUser(String myusername, String myPassword, String username) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return null;
        return me.ViewAquistionHistoryOfUser(username);
    }

    public static List<StorePurchase> usecase6_4B_WatchPurchesHistoryofStore(String myusername, String myPassword, String storename) {
        User me = System.getInstance().getMember(myusername, myPassword);
        if (me == null)
            return null;
        return me.ViewAquistionHistory(storename);
    }

    public static String usecase4_SystemStateString(String myusername, String myPassword) {
        String state =
                        "guests     : " + usecase4_WatchGuestCount(myusername, myPassword) + "\n" +
                        "members    : " + usecase4_WatchMemberCount(myusername, myPassword) + "\n" +
                        "managers   : " + usecase4_WatchManagerCount(myusername, myPassword) + "\n" +
                        "owners     : " + usecase4_WatchOwnerCount(myusername, myPassword) + "\n" +
                        "admins     : " + usecase4_WatchSYS_ManagerCount(myusername, myPassword) + "\n";

        if(state.equals(prev_state) ){
            return null;
        }

        prev_state = state;
        return state;
    }

    public static int usecase4_WatchOwnerCount(String myusername, String myPassword) {
        return System.OwnerLogin.get();
    }

    public static int usecase4_WatchMemberCount(String myusername, String myPassword) {
        return System.MemberLogin.get();
    }

    public static int usecase4_WatchGuestCount(String myusername, String myPassword) {
        return System.GuestLogin.get();
    }

    public static int usecase4_WatchManagerCount(String myusername, String myPassword) {
        return System.ManagerLogin.get();
    }

    public static int usecase4_WatchSYS_ManagerCount(String myusername, String myPassword) {
        return System.SYS_ManagerLogin.get();
    }


}
