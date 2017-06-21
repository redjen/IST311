package dao;

import hospital.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines methods for reading and writing account data.
 *
 * @see AccountDaoFactory
 */
public interface AccountDao {

   /**
    * Returns a list of all known accounts
    *
    * @return list of accounts
    */
   public ArrayList<Employee> getAccounts();

   /**
    * Searches for an account with the given login name and returns it
    *
    * @param employeeId the employee's login name
    * @return the account or null if no match was found
    */
   public Employee getAccountByLoginName(String employeeId);

   /**
    * Searches for an account with the given id and returns it
    *
    * @param id the employee's login name
    * @return the account or null if no match was found
    */
   public Employee getAccountById(long id);

   /**
    * Creates or updates the specified account in storage
    *
    * @param account the account to save
    */
   public void saveAccount(Employee account);

   /**
    * Updates accounts in storage
    *
    */
   public void saveAccounts();
   
   /**
    * Removes all accounts
    */
   public void reset();
}
