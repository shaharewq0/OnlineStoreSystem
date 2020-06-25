package tests.StressTests;

import Service_Layer.guest_accese.guest_accese;
import Service_Layer.member_accese.member_accese;

public class EasyTest implements Runnable {
    @Override
    public void run() {
        String user = RandomStringGenerator.randomString(10);
        int ggustID = guest_accese.ImNew();

        guest_accese.usecase2_2_guest_register(user, user);

        if(!guest_accese.usecase2_3_login(ggustID, user, user)){
            failedTestsCounter.failed();
            System.out.println("EasyTest: did not login");
        }

        if(! member_accese.usecase3_1_Logout(ggustID)){
            failedTestsCounter.failed();
            System.out.println("EasyTest: did not logout");
        }


    }



}
