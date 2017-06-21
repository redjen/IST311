package dao;

import hospital.Employee;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Integration test for reading the employee database
 *
 * The file itself is binary and the raw values can be found in
 * CreateEmployeeJava
 */
public class FileEmployeeDaoTest {

   private AccountDao dao;

   public FileEmployeeDaoTest() {
   }

   @Before
   public void setUp() throws AccountDaoException {
      dao = AccountDaoFactory.getDao();
   }

   @Test
   public void testGetData() {

      ArrayList<Employee> employees = dao.getAccounts();

      testGetDataHelper(employees.get(0), "test1", 100, "Mairead Âviâja", "Kravitz");
      testGetDataHelper(employees.get(1), "test2", 101, "Cathryn", "Struna");
      testGetDataHelper(employees.get(2), "test3", 102, "Tina", "Wallace");

   }

   @Test
   public void getEmployeeDao() {
      assertEquals(FileAccountDaoImpl.class, dao.getClass());
   }

   @Test
   public void getEmployeeByLoginName() {
      assertEquals("Kravitz", dao.getAccountByLoginName("test1").getLastName());
      assertNull(dao.getAccountByLoginName("nope"));
   }

   @Test
   public void getEmployeeByLoginNameNull() {
      assertNull(dao.getAccountByLoginName("nope"));
   }

   @Test
   public void getEmployeeById() {
      assertEquals(100, dao.getAccountById(100).getId());
   }

   @Test
   public void getEmployeeByIdNull() {
      assertNull(dao.getAccountById(3032));
   }

   @Test
   public void testValidatePassword() {
      ArrayList<Employee> employees = dao.getAccounts();

      assertTrue(employees.get(0).validatePassword("testpass1"));
      assertFalse(employees.get(0).validatePassword("incorrect"));
      assertTrue(employees.get(1).validatePassword("testpass2"));
      assertTrue(employees.get(2).validatePassword("testpass3"));
   }

   private void testGetDataHelper(Employee employee, String loginName, long id,
           String firstName, String lastName) {

      assertEquals(loginName, employee.getLoginName());
      assertEquals(id, employee.getId());
      assertEquals(firstName, employee.getFirstName());
      assertEquals(lastName, employee.getLastName());
   }
}