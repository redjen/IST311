package dao;

import hospital.Account;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Integration test for reading the account database
 *
 * The file itself is binary and the raw values can be found in
 * CreateEmployeeJava
 * 
 * @see CreateAccountData
 */
public class FileAccountDaoTest {

   private AccountDao dao;

   public FileAccountDaoTest() {
   }
  

   @Before
   public void setUp() throws AccountDaoException {
      dao = AccountDaoFactory.getDao();
   }
   
   @After
   public void tearDown() {
      dao.reset();
   }

   @Test
   public void testGetData() {

      ArrayList<Account> accounts = dao.getAccounts();

      testGetDataHelper(accounts.get(0), "test1", 100, "First1", "Last1");
      testGetDataHelper(accounts.get(1), "test2", 101, "First2", "Last2");
      testGetDataHelper(accounts.get(2), "test3", 102, "First3", "Last3");

   }

   @Test
   public void getAccountDao() {
      assertEquals(FileAccountDaoImpl.class, dao.getClass());
   }

   @Test
   public void getAccountByLoginName() {
      assertEquals("Last1", dao.getAccountByLoginName("test1").getLastName());
      assertNull(dao.getAccountByLoginName("nope"));
   }

   @Test
   public void getAccountByLoginNameNull() {
      assertNull(dao.getAccountByLoginName("nope"));
   }

   @Test
   public void getAccountById() {
      assertEquals(100, dao.getAccountById(100).getId());
   }

   @Test
   public void getAccountByIdNull() {
      assertNull(dao.getAccountById(3032));
   }

   @Test
   public void testValidatePassword() {
      ArrayList<Account> accounts = dao.getAccounts();

      assertTrue(accounts.get(0).validatePassword("testpass1"));
      assertFalse(accounts.get(0).validatePassword("incorrect"));
      assertTrue(accounts.get(1).validatePassword("testpass2"));
      assertTrue(accounts.get(2).validatePassword("testpass3"));
   }
   
   @Test
   public void testSaveAccount() {
      Account account = new Account(5000, "first5000", "last5000", "test5000", "pass5000", true);
      dao.saveAccount(account);
      testGetDataHelper(account, "test5000", 5000, "first5000", "last5000");
   }

   private void testGetDataHelper(Account account, String loginName, long id,
           String firstName, String lastName) {

      assertEquals(loginName, account.getLoginName());
      assertEquals(id, account.getId());
      assertEquals(firstName, account.getFirstName());
      assertEquals(lastName, account.getLastName());
   }
}
