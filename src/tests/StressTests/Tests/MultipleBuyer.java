package tests.StressTests.Tests;

import Domain.info.ProductDetails;
import Service_Layer.guest_accese.guest_accese;
import Service_Layer.member_accese.member_accese;
import Service_Layer.owner_accese.owner_accese;
import Service_Layer.userAddress;
import extornal.payment.CreditCard;
import org.junit.Assert;
import org.junit.Test;
import tests.AcceptanceTests.auxiliary.StoreDetails;
import tests.StressTests.Tools.RandomStringGenerator;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

public class MultipleBuyer {

    @Test
    public void MultipleBuyer() {
        String rnd = RandomStringGenerator.randomString(10);
        int gustID = guest_accese.ImNew();


        assertTrue(guest_accese.usecase2_2_guest_register(rnd, rnd));
        assertTrue(guest_accese.usecase2_3_login(gustID, rnd, rnd));

        assertTrue(member_accese.usecase3_2_OpenStore(gustID, new StoreDetails(rnd,rnd)));
        assertTrue(owner_accese.usecase4_1_1_AddingProdacsToStore(rnd,rnd,rnd, new ProductDetails(rnd, new LinkedList<>(), rnd, 6, 3)));



        Runnable r = () -> {
            String rnd2 = RandomStringGenerator.randomString(10);
            int gustID2 = guest_accese.ImNew();

             assertTrue(guest_accese.usecase2_2_guest_register(rnd2, rnd2));
            assertTrue(guest_accese.usecase2_3_login(gustID2, rnd2, rnd2));

            guest_accese.usecase2_6_saveProductToBasket(gustID2, rnd, rnd, 2);
            guest_accese.usecase2_8_Purchase_products(gustID2, new CreditCard("123", "123", "123", "123", "123"), new userAddress("abc","abc","abc","abc","abc"));
        };

        Thread u1 = new Thread(r);
        Thread u2 = new Thread(r);
        Thread u3 = new Thread(r);
        Thread u4 = new Thread(r);
        Thread u5 = new Thread(r);

        u1.start();
        u2.start();
        u3.start();
        u4.start();
        u5.start();
        try {
            u1.join();
            u2.join();
            u3.join();
            u4.join();
            u5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(guest_accese.searchProductByName(rnd, rnd).getAmount() >= 0);
    }



}
