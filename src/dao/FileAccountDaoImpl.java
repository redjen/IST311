package dao;

import hospital.Employee;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * The FileAccountDaoImpl class is a simple implementation of an employee
 * database using a pregenerated data file. It provides methods for loading,
 * searching, and updating the database.
 * 
 * This class should not be instantiated directly, but instead by using the
 * AccountDaoFactory.
 * 
 * @see AccountDaoFactory
 *
 */
public class FileAccountDaoImpl implements AccountDao {

   private final static String DATA_PATH = "resources/";
   private final static String DATA_FILE = DATA_PATH + "employees.dat";

   /* A collection that emulates an employee table indexed by login name */
   private final HashMap<String, Employee> employeesByLoginName;

   /* A collection that emulates and employee table indexed by ID */
   private final HashMap<Long, Employee> employeesById;

   /* A collection that emulates an employee table */
   private final ArrayList<Employee> employees;

   /**
    * Constructs a new Dao and loads the accounts
    *
    * @throws dao.AccountDaoException if the employee data file is missing,
    * empty, or cannot be parsed
    */
   public FileAccountDaoImpl() throws AccountDaoException {
      // TODO implement custom exceptions in the next version

      this.employees = new ArrayList<>();
      this.employeesByLoginName = new HashMap<>();
      this.employeesById = new HashMap<>();

      loadRepository();
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public Employee getAccountByLoginName(String loginName) {
      return employeesByLoginName.get(loginName);
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public Employee getAccountById(long id) {
      return employeesById.get(id);
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public ArrayList<Employee> getAccounts() {
      ArrayList<Employee> employeeList = new ArrayList<>(employees);
      return employeeList;
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public void saveAccount(Employee account) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public void saveAccounts(List<Employee> accounts) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   /**
    * Loads the employee repository from the data file
    *
    * @throws AccountDaoException
    */
   private void loadRepository() throws AccountDaoException {

      File data = new File(DATA_FILE);

      try (DataInputStream dis = new DataInputStream(new FileInputStream(data))) {

         int numRecords = dis.readInt();

         for (int i = 0; i < numRecords; i++) {
            String loginId = dis.readUTF();
            long id = dis.readLong();
            String firstName = dis.readUTF();
            String lastName = dis.readUTF();
            byte[] passwordHash = new byte[16];
            dis.read(passwordHash);

            Employee employee = new Employee(id, firstName, lastName, loginId, passwordHash);
            employees.add(employee);
            employeesById.put(employee.getId(), employee);
            employeesByLoginName.put(employee.getLoginName(), employee);
         }

      } catch (FileNotFoundException ex) {
         throw new AccountDaoException(String.format("Employee data %s missing.",
                 DATA_FILE));
      } catch (IOException ex) {
         throw new AccountDaoException(String.format("Error reading employee data %s.",
                 DATA_FILE));
      }
   }
   
   private void readAccount() {
      
   }
   
   private void writeAccount() {
      
   }
}
