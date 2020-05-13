package tests.AcceptanceTests.System;

import org.junit.Test;
import tests.AcceptanceTests.BaseAccTest;

/* TODO:
*   - implement initialize
*   - create DummyPayment and DummySupply
*   - create system manager
* */

public class InitializeTest extends BaseAccTest {
    @Test
    public void initialize() {
        system.init();
    }
}
