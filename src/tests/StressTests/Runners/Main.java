package tests.StressTests.Runners;

import org.junit.Test;

import tests.StressTests.Tools.failedTestsCounter;


import static org.junit.Assert.assertEquals;




public class Main implements Runnable {

    @Override
    public void run() {
        new StresTestRunner().run();
    }

   @Test
    public void testall() {
      run();
      assertEquals(0, failedTestsCounter.getCounter());
    }

   // public static void main(String[] args) {
    //    new StressRunner().run();
    //}
}