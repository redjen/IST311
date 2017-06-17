package hospital;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeTest extends PersonTestBase<Employee> {

   private final static long TEST_EMPLOYEE_1_ID = 1001;
   private final static String TEST_EMPLOYEE_1_LOGINNAME = "desrosierA";
   private final static String TEST_EMPLOYEE_1_FIRSTNAME = "Anaïs";
   private final static String TEST_EMPLOYEE_1_FIRSTNAME_CHANGED = "Jane";
   private final static String TEST_EMPLOYEE_1_LASTNAME = "Desrosiers";
   private final static String TEST_EMPLOYEE_1_LASTNAME_CHANGED = "Doe";
   private final static String TEST_EMPLOYEE_1_PASSWORD = "P@ssw0rd!";

   public EmployeeTest() {
      expected_test_id = TEST_EMPLOYEE_1_ID;
      expected_first_name = TEST_EMPLOYEE_1_FIRSTNAME;
      expected_first_name_changed = TEST_EMPLOYEE_1_FIRSTNAME_CHANGED;
      expected_last_name = TEST_EMPLOYEE_1_LASTNAME;
      expected_last_name_changed = TEST_EMPLOYEE_1_LASTNAME_CHANGED;
      expected_full_name = String.format("%s %s", TEST_EMPLOYEE_1_FIRSTNAME, TEST_EMPLOYEE_1_LASTNAME);
      expected_full_name_last_first = String.format("%s, %s", TEST_EMPLOYEE_1_LASTNAME, TEST_EMPLOYEE_1_FIRSTNAME);
      expected_public_display_name = TEST_EMPLOYEE_1_LOGINNAME;
   }

   @Before
   @Override
   public void setUp() {
      person = new Employee(expected_test_id, expected_first_name, expected_last_name, TEST_EMPLOYEE_1_LOGINNAME, TEST_EMPLOYEE_1_PASSWORD);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructorZeroLengthPasswordHash() {
      byte[] emptyHash = {};
      Employee employee = new Employee(expected_test_id, expected_first_name, expected_last_name, TEST_EMPLOYEE_1_LOGINNAME, emptyHash);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructorZeroLengthPassword() {
      Employee employee = new Employee(expected_test_id, expected_first_name, expected_last_name, TEST_EMPLOYEE_1_LOGINNAME, "");
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructorNullPassword() {
      String password = null;
      Employee employee = new Employee(expected_test_id, expected_first_name, expected_last_name, TEST_EMPLOYEE_1_LOGINNAME, password);
   }

   @Test
   @Override
   public void testGetPublicDisplayName() {
      assertEquals(expected_public_display_name, person.getPublicDisplayName());
   }

   @Test
   public void testValidatePasswordTrue() {
      assertTrue(person.validatePassword(TEST_EMPLOYEE_1_PASSWORD));
   }

   @Test
   public void testValidatePasswordFalse() {
      assertFalse(person.validatePassword("incorrect password"));
   }

   @Test
   public void testValidatePasswordNull() {
      assertFalse(person.validatePassword(null));
   }

   @Test
   public void testGetLoginName() {
      assertEquals(TEST_EMPLOYEE_1_LOGINNAME, person.getLoginName());
   }

}
