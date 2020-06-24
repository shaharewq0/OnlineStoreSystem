package tests.StressTests;

public class AllTests implements Runnable {
    @Override
    public void run() {
        new EasyTest().run();
    }
}
