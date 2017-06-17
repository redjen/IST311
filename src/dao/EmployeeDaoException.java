package dao;


public class EmployeeDaoException extends Exception {

   public EmployeeDaoException() {
      super();
   }
   
   EmployeeDaoException(String message) {
      super(message);
   }
   
   EmployeeDaoException(String message, Throwable cause) {
      super(message, cause);
   }

}
