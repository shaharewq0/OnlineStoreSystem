package tests.StressTests.Tests;

import Service_Layer.guest_accese.guest_accese;
import Service_Layer.member_accese.member_accese;
import org.junit.Test;
import tests.AcceptanceTests.auxiliary.StoreDetails;
import tests.StressTests.Tools.RandomStringGenerator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OpenStoreTest {
    @Test
    public void openStore() {
        String rnd = RandomStringGenerator.randomString(10);
        int guestID = guest_accese.ImNew();

        assertTrue(guest_accese.usecase2_2_guest_register(rnd, rnd));
        assertTrue(guest_accese.usecase2_3_login(guestID, rnd, rnd));

        assertTrue(member_accese.usecase3_2_OpenStore(guestID, new StoreDetails(rnd,rnd)));
    }
}
