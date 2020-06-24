package Service_Layer.sys_manager_accese;

import Domain.UserClasses.User;
import Domain.UserClasses.UserPurchase;
import Domain.Store.StorePurchase;
import Domain.store_System.System;

import java.util.List;

public class sys_mangaer_accese {

    public static boolean InitSystem(String myusername, String MyPassword) {
        System.getInstance().init(myusername, MyPassword);
        return true;

    }

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

    public static int usecase4_WatchOwnerCount(String myusername, String myPassword, String storename) {
        return System.OwnerLogin;
    }

    public static int usecase4_WatchMemberCount(String myusername, String myPassword, String storename) {
        return System.MemberLogin;
    }

    public static int usecase4_WatchGuestCount(String myusername, String myPassword, String storename) {
        return System.GuestLogin;
    }

    public static int usecase4_WatchManagerCount(String myusername, String myPassword, String storename) {
        return System.ManagerLogin;
    }

    public static int usecase4_WatchSYS_ManagerCount(String myusername, String myPassword, String storename) {
        return System.SYS_ManagerLogin;
    }


}
