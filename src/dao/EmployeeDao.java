package dao;

import hospital.Employee;
import java.util.ArrayList;

/**
 * Provides read-only access to the employee data repository
 * 
 */
public interface EmployeeDao {
   public Employee getEmployeeByLoginName(String employeeId);
   public Employee getEmployeeById(long id);
   public ArrayList<Employee> getEmployees();
}
