package tests.StressTests.Tools;

import java.util.concurrent.atomic.AtomicInteger;

public class alloc_pid{
    private static final AtomicInteger pid = new AtomicInteger(0);

    public static int getPID() {
        return pid.incrementAndGet();
    }
}