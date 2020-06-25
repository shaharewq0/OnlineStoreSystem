package tests.StressTests.Runners;

import tests.AcceptanceTests.GuestBuyer.GetStoreDetailsTest;
import tests.StressTests.Tools.failedTestsCounter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class StresTestRunner implements Runnable {

    private static final int USERS = 5;
    private static final int THREAD_NUM = Runtime.getRuntime().availableProcessors();


    private ExecutorService executor;

    @Override
    public void run() {

        executor = Executors.newFixedThreadPool(THREAD_NUM);

        // System.out.println("temp");
        GetStoreDetailsTest.setUpClass();
        for(int i = 1; i<= USERS; i++){
            executor.execute(new JunitTestRunner()); //CHANGE HERE <----------------------------------------------------------------------
        }


        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
        System.out.println("\n#Failures : " + failedTestsCounter.getCounter());

    }
}