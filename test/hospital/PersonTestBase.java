package hospital;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public abstract class PersonTestBase<T extends Person> {

   protected long expected_test_id;
   protected String expected_first_name;
   protected String expected_first_name_changed;
   protected String expected_last_name;
   protected String expected_last_name_changed;
   protected String expected_full_name;
   protected String expected_full_name_last_first;
   protected String expected_public_display_name;
   protected T person;

   public PersonTestBase() {
   }

   @Before
   public void setUp() {

   }
   
   @Test
   public void testGetId() {
      assertEquals(expected_test_id, person.getId());
   }

   @Test
   public void testGetFullName() {
      assertEquals(expected_full_name, person.getFullName());
   }

   @Test
   public void testGetFullNameLastFirst() {
      assertEquals(expected_full_name_last_first, person.getFullNameLastFirst());
   }

   @Test
   public void testGetFirstName() {
      assertEquals(expected_first_name, person.getFirstName());
   }

   @Test
   public void testSetFirstName() {
      person.setFirstName(expected_first_name_changed);
      assertEquals(expected_first_name_changed, person.getFirstName());
   }

   @Test
   public void testGetLastName() {
      assertEquals(expected_last_name, person.getLastName());
   }
   
   @Test
   public void testSetLastName() {
      person.setLastName(expected_last_name_changed);
      assertEquals(expected_last_name_changed, person.getLastName());
   }

   @Test
   public void testGetPublicDisplayName() {
      assertEquals(expected_public_display_name, person.getPublicDisplayName());
   }
}
