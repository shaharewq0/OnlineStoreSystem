package tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.AcceptanceTests.AllAcceptanceTests;
import tests.IntegrationTests.AllIntegrationTests;
import tests.UnitTests.AllUnitTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllAcceptanceTests.class,
        AllIntegrationTests.class,
        AllUnitTests.class,
})

public class AllTests {
}
