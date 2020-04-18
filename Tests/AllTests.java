import AcceptanceTests.AllAcceptanceTests;
import IntegrationTests.AllIntegrationTests;
import UnitTests.AllUnitTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllAcceptanceTests.class,
        AllIntegrationTests.class,
        AllUnitTests.class,
})

public class AllTests {
}
