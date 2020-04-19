package tests.UnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
test_supply.class,
Payment_Tests.class,
shoppingBasketTest.class,
StoreTest.class,
SystemTest.class,
UserTest.class,
StoreOwner_ImpTest.class,
PassProtocol_ImpTest.class,
EventLoggerTest.class,
ErrorLoggerTest.class
})

public class AllUnitTests {
}
