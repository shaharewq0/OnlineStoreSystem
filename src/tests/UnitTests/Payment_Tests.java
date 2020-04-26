package tests.UnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import extornal.payment.Isracard;
import extornal.payment.MyPaymentSystem;
import extornal.payment.MyPaymentSystem_Driver;
import extornal.payment.Pay_pal;
import extornal.payment.bankAccount;

public class Payment_Tests {

	private bankAccount bank1 = new bankAccount("bank1", 99933);
	private bankAccount bank2 = new bankAccount("bank2", 5366);
	MyPaymentSystem mss = null;
	@Before
	public void setUp() throws Exception {
		mss = new MyPaymentSystem_Driver();
	}

	@Test
	public void testPay() {
		try {
			setUp();
		} catch (Exception e) {
			fail("init error");
			e.printStackTrace();
		}
		assertTrue(mss.pay(bank1, 5555));

		assertFalse(mss.pay(null, 5555));

		assertFalse(mss.pay(bank1, -1));
		
	}
	
	@Test
	public void testChangetype() {
		try {
			setUp();
		} catch (Exception e) {
			fail("init error");
			e.printStackTrace();
		}
		mss.changePaymentMethed(Isracard.name);
		assertTrue(mss.getPaymentMethed() instanceof Isracard);
		mss.changePaymentMethed(Pay_pal.name);
		assertTrue(mss.getPaymentMethed() instanceof Pay_pal);
		assertFalse(mss.getPaymentMethed() instanceof Isracard);
		
	}

}
