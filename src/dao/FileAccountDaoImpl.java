package dao;

import hospital.Employee;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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

   private final static String ACCOUNT_DATA = "build/employees.dat";
   private final static String TEST_DATA = "resources/accounts.txt";

   /* A collection that emulates an employee table indexed by login name */
   private final HashMap<String, Employee> employeesByLoginName;

   /* A collection that emulates and employee table indexed by ID */
   private final HashMap<Long, Employee> employeesById;

   /* A collection that emulates an employee table */
   private final ArrayList<Employee> employees;
//
//   private DataInputStream dis;
//   private DataOutputStream dos;

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
      int numRecords = accounts.size();

      try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(ACCOUNT_DATA)))) {
         dos.writeInt(numRecords);
         for (Employee account : accounts) {
            writeAccount(account, dos);
         }
         dos.flush();
      } catch (IOException ex) {
         Logger.getLogger(FileAccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * Loads the employee repository from the data file
    *
    * @throws AccountDaoException
    */
   private void loadRepository() throws AccountDaoException {

      File data = new File(ACCOUNT_DATA);

      if (!data.exists()) {
         loadTestData();
      }

      try (DataInputStream dis = new DataInputStream(new FileInputStream(data))) {

         int numRecords = dis.readInt();

         for (int i = 0; i < numRecords; i++) {
            Employee employee = readAccount(dis);
            employees.add(employee);
            employeesById.put(employee.getId(), employee);
            employeesByLoginName.put(employee.getLoginName(), employee);
         }

      } catch (FileNotFoundException ex) {
         throw new AccountDaoException(String.format("Employee data %s missing.",
                 ACCOUNT_DATA));
      } catch (IOException ex) {
         throw new AccountDaoException(String.format("Error reading employee data %s.",
                 ACCOUNT_DATA), ex);
      }
   }

   private Employee readAccount(DataInputStream dis) throws IOException {

      long id = dis.readLong();
      String loginId = dis.readUTF();
      String firstName = dis.readUTF();
      String lastName = dis.readUTF();
      byte[] passwordHash = new byte[16];
      dis.read(passwordHash);

      return new Employee(id, firstName, lastName, loginId, passwordHash);
   }

   private void writeAccount(Employee employee, DataOutputStream dos) throws IOException {

      dos.writeLong(employee.getId());
      dos.writeUTF(employee.getLoginName());
      dos.writeUTF(employee.getFirstName());
      dos.writeUTF(employee.getLastName());
      dos.write(employee.getPasswordHash());
   }

   private void loadTestData() {
      System.out.println("Writing test data");
      ArrayList<Employee> list = new ArrayList<>();
      try (Scanner in = new Scanner(new File(TEST_DATA))) {
         while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.charAt(0) != '#') {
               String[] tokens = line.split(",");
               Employee employee = new Employee(Long.parseLong(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4]);
               list.add(employee);
            }
         }
         saveAccounts(list);
      } catch (FileNotFoundException ex) {
         Logger.getLogger(FileAccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
