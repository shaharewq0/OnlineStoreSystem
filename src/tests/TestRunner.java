package tests;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import tests.AcceptanceTests.AllAcceptanceTests;
import tests.IntegrationTests.AllIntegrationTests;
import tests.UnitTests.AllUnitTests;

/*  TODO:
        doesn't sure we need this class,
        can help in case we want to run only several tests
*/

public class TestRunner {
    private static List<Class> TestsToRun = Arrays.asList(
            AllAcceptanceTests.class,
            AllIntegrationTests.class,
            AllUnitTests.class
    );

    public static void main(String[] args) {
        boolean success = true;
        for (Class testSuite : TestsToRun) {
            //System.out.println(testSuite.getPackageName() + ":");
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