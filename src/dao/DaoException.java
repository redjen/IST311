package dao;


/**
 * DaoException is a custom exception for use when working with the DAO classes
 * 
 * @author redjen
 */
public class DaoException extends Exception {

   public DaoException() {
      super();
   }
   
   DaoException(String message) {
      super(message);
   }
   
   DaoException(String message, Throwable cause) {
      super(message, cause);
   }

}
