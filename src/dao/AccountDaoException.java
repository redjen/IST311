package dao;


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
