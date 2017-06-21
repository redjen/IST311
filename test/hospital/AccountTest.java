package hospital;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest extends PersonTestBase<Account> {

   private final static long TEST_ACCOUNT_1_ID = 1001;
   private final static String TEST_ACCOUNT_1_LOGINNAME = "desrosierA";
   private final static String TEST_ACCOUNT_1_FIRSTNAME = "Anaïs";
   private final static String TEST_ACCOUNT_1_FIRSTNAME_CHANGED = "Jane";
   private final static String TEST_ACCOUNT_1_LASTNAME = "Desrosiers";
   private final static String TEST_ACCOUNT_1_LASTNAME_CHANGED = "Doe";
   private final static String TEST_ACCOUNT_1_PASSWORD = "P@ssw0rd!";
   private final static boolean TEST_ACCOUNT_1_EMPLOYEE = true;

   public AccountTest() {
      expected_test_id = TEST_ACCOUNT_1_ID;
      expected_first_name = TEST_ACCOUNT_1_FIRSTNAME;
      expected_first_name_changed = TEST_ACCOUNT_1_FIRSTNAME_CHANGED;
      expected_last_name = TEST_ACCOUNT_1_LASTNAME;
      expected_last_name_changed = TEST_ACCOUNT_1_LASTNAME_CHANGED;
      expected_full_name = String.format("%s %s", TEST_ACCOUNT_1_FIRSTNAME, TEST_ACCOUNT_1_LASTNAME);
      expected_full_name_last_first = String.format("%s, %s", TEST_ACCOUNT_1_LASTNAME, TEST_ACCOUNT_1_FIRSTNAME);
      expected_public_display_name = TEST_ACCOUNT_1_LOGINNAME;
   }

   @Before
   @Override
   public void setUp() {
      person = new Account(expected_test_id, expected_first_name, expected_last_name, 
              TEST_ACCOUNT_1_LOGINNAME, TEST_ACCOUNT_1_PASSWORD, TEST_ACCOUNT_1_EMPLOYEE);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructorZeroLengthPasswordHash() {
      byte[] emptyHash = {};
      Account account = new Account(expected_test_id, expected_first_name, 
              expected_last_name, TEST_ACCOUNT_1_LOGINNAME, emptyHash, TEST_ACCOUNT_1_EMPLOYEE);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructorZeroLengthPassword() {
      Account account = new Account(expected_test_id, expected_first_name, 
              expected_last_name, TEST_ACCOUNT_1_LOGINNAME, "", TEST_ACCOUNT_1_EMPLOYEE);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructorNullPassword() {
      String password = null;
      Account account = new Account(expected_test_id, expected_first_name, 
              expected_last_name, TEST_ACCOUNT_1_LOGINNAME, password, TEST_ACCOUNT_1_EMPLOYEE);
   }
   
   @Test
   public void testIsEmployee() {
      assertEquals(TEST_ACCOUNT_1_EMPLOYEE, person.isEmployee());
   }

   @Test
   @Override
   public void testGetPublicDisplayName() {
      assertEquals(expected_public_display_name, person.getPublicDisplayName());
   }

   @Test
   public void testValidatePasswordTrue() {
      assertTrue(person.validatePassword(TEST_ACCOUNT_1_PASSWORD));
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
      assertEquals(TEST_ACCOUNT_1_LOGINNAME, person.getLoginName());
   }

}
