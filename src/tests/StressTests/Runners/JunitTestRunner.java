package tests.StressTests.Runners;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import tests.StressTests.Tests.*;
import tests.StressTests.Tools.alloc_pid;
import tests.StressTests.Tools.failedTestsCounter;

import java.util.Arrays;
import java.util.List;


public class JunitTestRunner implements Runnable {

    private int pid;

    private static List<Class> TestsToRun = Arrays.asList(
            LoginTest.class,
            OpenStoreTest.class,
            AddProfuct2Store.class,
            Add_product_cartTest.class,
            Purchase.class,
            MultipleBuyer.class
    );

    @Override
    public void run() {
        pid = alloc_pid.getPID();

        for (Class testSuite : TestsToRun) {
            runTests(testSuite);
            System.out.println();
        }

    }

    private boolean runTests(Class testSuite) {
        Result result = JUnitCore.runClasses(testSuite);
        for (Failure failure : result.getFailures()) {
            System.out.println(String.format("ERROR in runner#%d : %s", pid, failure.toString()));
            failedTestsCounter.failed();
        }
        return result.wasSuccessful();
    }
}
