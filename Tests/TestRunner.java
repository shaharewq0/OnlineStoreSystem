import AcceptanceTests.AllAcceptanceTests;
import IntegrationTests.AllIntegrationTests;
import UnitTests.AllUnitTests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Arrays;
import java.util.List;

public class TestRunner {
    private static List<Class> TestsToRun = Arrays.asList(
            AllAcceptanceTests.class,
            AllIntegrationTests.class,
            AllUnitTests.class
    );

    public static void main(String[] args) {
        boolean success = true;
        for (Class testSuite : TestsToRun) {
            System.out.println(testSuite.getPackageName() + ":");
            success &= runTests(testSuite);
            System.out.println();
        }
        if (success)
            System.out.println("All tests passed!");

    }

    private static boolean runTests(Class testSuite) {
        Result result = JUnitCore.runClasses(testSuite);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        return result.wasSuccessful();
    }
}