package tests.StressTests;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import tests.AcceptanceTests.AllAcceptanceTests;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


class alloc_pid{
    private static final AtomicInteger pid = new AtomicInteger(0);

    public static int getPID() {
        return pid.incrementAndGet();
    }
}


class testRunner implements Runnable {

    private int pid;

    private static List<Class> TestsToRun = Arrays.asList(
            AllAcceptanceTests.class
            //AllIntegrationTests.class,
            //AllUnitTests.class
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


class stresstest implements Runnable {

    private static final int USERS = 10;
    private static final int THREAD_NUM = Runtime.getRuntime().availableProcessors();


    private ExecutorService executor;

    @Override
    public void run() {

        executor = Executors.newFixedThreadPool(THREAD_NUM);

        for(int i = 1; i<= USERS; i++){
            executor.execute(new testRunner()); //CHANGE HERE <----------------------------------------------------------------------
        }


        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
        System.out.println("\n\n\n\n#Failures : " + failedTestsCounter.getCounter());

    }
}









public class StressRunner implements Runnable {

    @Override
    public void run() {
        new stresstest().run();
    }

   @Test
    public void payWithCard() {
        run();
        assertEquals(0, failedTestsCounter.getCounter());
    }


    public static void main(String[] args) {
        new StressRunner().run();
    }
}