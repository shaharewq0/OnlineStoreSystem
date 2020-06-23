package tests.UnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import extornal.payment.Isracard;
import extornal.payment.MyPaymentSystem;
import extornal.payment.MyPaymentSystem_Driver;
import extornal.payment.Pay_pal;
import extornal.payment.CreditCard;

public class Payment_Tests {

	private CreditCard bank1 = new CreditCard("123", "02/23", "311", "yosi yosi", "1234");
	private CreditCard bank2 =new CreditCard("321", "01/24", "476", "pil pilon", "3214");
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
