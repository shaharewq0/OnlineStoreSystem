package tests.AcceptanceTests.System;

import org.junit.Test;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.auxiliary.DummyPayment;
import tests.AcceptanceTests.auxiliary.DummySupply;

/* TODO:
*   - implement initialize
*   - create DummyPayment and DummySupply
*   - create system manager
* */

public class InitializeTest extends BaseAccTest {
    @Test
    public void initialize() {
        system.init(new DummyPayment(), new DummySupply());
    }
}
