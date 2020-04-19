package extornal.payment.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import extornal.payment.Isracard;
import extornal.payment.MyPaymentSystem;
import extornal.payment.MyPaymentSystem_Driver;
import extornal.payment.Pay_pal;

public class Payment_Tests {

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
		assertTrue(mss.pay(142112345, 5555));

		assertFalse(mss.pay(-1, 5555));

		assertFalse(mss.pay(142112345, -1));
		
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
