package notifcation;

import hospital.Patient;
import hospital.PatientStatus;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maximdumont
 */
public class NotificationQueueingServiceTest {
    
    public NotificationQueueingServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of subscribeAll method, of class NotificationQueueingService.
     */
    @Test
    public void testSubscribeAll() {
        System.out.println("subscribeAll");
        Patient[] patients = new Patient[1];
        patients[0] = new Patient("Test", "Test", new Date(), "patientId");
        patients[0].setStatus(PatientStatus.TREATMENT);
        NotificationQueueingService instance = new NotificationQueueingService();
        instance.subscribeAll(patients);
        
        instance.change(patients[0].getPatientId(), PatientStatus.WAITING);
        instance.publish();
    }

    /**
     * Test of subscribe method, of class NotificationQueueingService.
     */
    @Test
    public void testSubscribe() {
        System.out.println("subscribe");
        Patient patient =new Patient("Test", "Test", new Date(), "patientId");
        NotificationQueueingService instance = new NotificationQueueingService();
        instance.subscribe(patient);
        
        instance.change(patient.getPatientId(), PatientStatus.RECOVERY);
        instance.publish(patient.getPatientId());
    }
}
