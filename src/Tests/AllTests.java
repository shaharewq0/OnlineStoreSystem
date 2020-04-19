package Tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Tests.AcceptanceTests.AllAcceptanceTests;
import Tests.IntegrationTests.AllIntegrationTests;
import Tests.UnitTests.AllUnitTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllAcceptanceTests.class,
        AllIntegrationTests.class,
        AllUnitTests.class,
})

public class AllTests {
}
