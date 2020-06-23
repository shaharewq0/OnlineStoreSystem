package DAL;

import Domain.Policies.Acquisitions.AcqMaxAmount;
import Domain.Policies.Acquisitions.AcqMinAmount;
import Domain.Policies.Acquisitions.Acquisition;
import Domain.Policies.Acquisitions.AcquisitionPolicy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AcquisitionPolicy_DATest {
    AcquisitionPolicy_DA da;
    AcquisitionPolicy ap;
    Acquisition a,b;

    @Before
    public void setUp() throws Exception {
        da=new AcquisitionPolicy_DA();
        ap=new AcquisitionPolicy();
        a=new AcqMaxAmount("shahar",12);
        b=new AcqMinAmount("shahar",100);
        ap.addAcquisitionPolicy(a);
        ap.addAcquisitionPolicy(b);
    }

    @Test
    public void add(){
        da.add(ap);
        assertTrue(da.getAll().size()==1);
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {
    }
}