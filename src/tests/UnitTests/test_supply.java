package tests.UnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import extornal.supply.MySupplySystem;
import extornal.supply.MySupplySystem_Driver;
import extornal.supply.Packet_Of_Prodacts;
import extornal.supply.Rami_Lavy;
import extornal.supply.WallMart;
import extornal.supply.inventory;

public class test_supply {

	public static String value = "empty";
	MySupplySystem_Driver mss = null;
	Packet_Of_Prodacts pop = null;
	inventory in = null;

	@Before
	public void setUp() throws Exception {
		mss = new MySupplySystem_Driver();
		pop = new Packet_Of_Prodacts();

//		pop.items.add("obja");
//		pop.items.add("objb");
//		pop.items.add("objc");

	
	}

	@Test
	public void test_supplying() {
		try {
			setUp();
		} catch (Exception e) {
			fail("init error");
			e.printStackTrace();
		}
		mss.changeSupplayer(Rami_Lavy.name);
		mss.order(pop, in);	
//		assertTrue("checking item resive", pop.items.get(0).compareTo(value)==0);
//		mss.changeSupplayer(WallMart.name);
//		mss.order(pop, in);	
//		assertTrue("checking item resive", pop.items.get(1).compareTo(value)==0);
	}
	
	@Test
	public void test_supplyer_change() {
		try {
			setUp();
		} catch (Exception e) {
			fail("init error");
			e.printStackTrace();
		}
		mss.changeSupplayer(Rami_Lavy.name);
		assertTrue("checking currect supplyer", mss.current_supplyer instanceof Rami_Lavy);
		mss.changeSupplayer(WallMart.name);
		assertTrue("checking currect supplyer", mss.current_supplyer instanceof WallMart);
	}

}
