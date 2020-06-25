package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.AcceptanceTests.AllAcceptanceTests;
import tests.UnitTests.AllUnitTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllAcceptanceTests.class,
        //AllIntegrationTests.class,
        AllUnitTests.class,
//        AllDALTests.class
})

public class AllTests {
}
