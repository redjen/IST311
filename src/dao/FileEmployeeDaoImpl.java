package dao;

import hospital.Employee;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * The FileEmployeeDaoImpl class is a simple implementation of an employee
 * database. It provides methods to look up one or more employees.
 *
 * Create, update, and delete operations are not provided as employee account
 * management is out of scope for this project.
 *
 */
public class FileEmployeeDaoImpl implements EmployeeDao {

   private final static String DATA_FILE = "resources/employees.dat";

   /* A collection that emulates an employee table indexed by login name */
   private final HashMap<String, Employee> employeesByLoginName;

   /* A collection that emulates and employee table indexed by ID */
   private final HashMap<Long, Employee> employeesById;

   /* A collection that emulates an employee table */
   private final ArrayList<Employee> employees;

   private final static Logger logger = Logger.getLogger("FileEmployeeDao");

   /**
    * Constructs a new Dao
    *
    * @throws dao.EmployeeDaoException if the employee data file is missing,
    * empty, or cannot be parsed
    */
   public FileEmployeeDaoImpl() throws EmployeeDaoException {
      // TODO implement custom exceptions in the next version

      this.employees = new ArrayList<>();
      this.employeesByLoginName = new HashMap<>();
      this.employeesById = new HashMap<>();

      loadRepository();
   }

   /**
    * Looks up and returns one Employee from the repository
    *
    * @param loginName the login name of the employee
    * @return the Employee or null if not found
    */
   @Override
   public Employee getEmployeeByLoginName(String loginName) {
      return employeesByLoginName.get(loginName);
   }

   @Override
   public Employee getEmployeeById(long id) {
      return employeesById.get(id);
   }

   /**
    * Returns all employees from the repository
    *
    * @return ArrayList of employees
    */
   @Override
   public ArrayList<Employee> getEmployees() {
      return employees;
   }

   /**
    * Loads the employee repository from the data file
    *
    * @throws EmployeeDaoException
    */
   private void loadRepository() throws EmployeeDaoException {

      File data = new File(DATA_FILE);      

      try(DataInputStream dis = new DataInputStream(new FileInputStream(data))) {

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
         throw new EmployeeDaoException(String.format("Employee data %s missing.",
                 DATA_FILE));
      } catch (IOException ex) {
         throw new EmployeeDaoException(String.format("Error reading employee data %s.",
                 DATA_FILE));
      } 
   }

}
