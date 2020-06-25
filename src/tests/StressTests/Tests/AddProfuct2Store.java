package tests.StressTests.Tests;

import Domain.info.ProductDetails;
import Service_Layer.guest_accese.guest_accese;
import Service_Layer.member_accese.member_accese;
import Service_Layer.owner_accese.owner_accese;
import org.junit.Test;
import tests.AcceptanceTests.auxiliary.StoreDetails;
import tests.StressTests.Tools.RandomStringGenerator;

import java.util.LinkedList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddProfuct2Store {
    @Test
    public void addproduct() {
        String rnd = RandomStringGenerator.randomString(10);
        int guestID = guest_accese.ImNew();

        assertTrue(guest_accese.usecase2_2_guest_register(rnd, rnd));
        assertTrue(guest_accese.usecase2_3_login(guestID, rnd, rnd));

        assertTrue(member_accese.usecase3_2_OpenStore(guestID, new StoreDetails(rnd,rnd)));
        assertTrue(owner_accese.usecase4_1_1_AddingProdacsToStore(rnd,rnd,rnd, new ProductDetails(rnd, new LinkedList<>(), rnd, 10, 3)));
    }
}
