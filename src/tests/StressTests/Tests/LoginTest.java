package tests.StressTests.Tests;

import Service_Layer.guest_accese.guest_accese;
import Service_Layer.member_accese.member_accese;
import org.junit.Assert;
import org.junit.Test;
import tests.StressTests.Tools.RandomStringGenerator;

public class LoginTest {
    @Test
    public void login() {
        String user = RandomStringGenerator.randomString(10);
        int ggustID = guest_accese.ImNew();


        Assert.assertTrue(guest_accese.usecase2_2_guest_register(user, user));
        Assert.assertTrue(guest_accese.usecase2_3_login(ggustID, user, user));
        Assert.assertTrue(member_accese.usecase3_1_Logout(ggustID));

    }
}
