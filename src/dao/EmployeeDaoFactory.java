package dao;

/**
 * Generates EmployeeDao instances
 * 
 */
public class EmployeeDaoFactory {

   public EmployeeDaoFactory() {
   }

   public static EmployeeDao getEmployeeDao() throws EmployeeDaoException{
      EmployeeDao dao = new FileEmployeeDaoImpl();
      return dao;
   }
}
