package tests.StressTests;

import java.util.concurrent.atomic.AtomicInteger;

class failedTestsCounter{
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static int getCounter() {
        return counter.get();
    }

    public static void failed() {
        counter.getAndIncrement();
    }
}