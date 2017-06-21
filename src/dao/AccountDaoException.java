package dao;


/**
 * A custom exception for use when working with the DAO classes
 */
public class AccountDaoException extends Exception {

   public AccountDaoException() {
      super();
   }
   
   AccountDaoException(String message) {
      super(message);
   }
   
   AccountDaoException(String message, Throwable cause) {
      super(message, cause);
   }

}
