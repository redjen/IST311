package hospital;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Employee class represents a hospital employee that can log in and access
 * patient data.
 */
public class Employee extends Person implements Authenticatable {

   private final String loginName;
   private final byte[] passwordHash;

   private final static String PASSWORD_ALGORITHM = "MD5";
   
   private final static Logger logger = Logger.getLogger("Employee");

   /**
    * Constructs a new Employee using a plain-text password
    *
    * The password is hashed on object creation and persisted.
    *
    * @param id unique identifier
    * @param loginName the name
    * @param firstName
    * @param lastName
    * @param password
    */
   public Employee(long id, String firstName, String lastName, String loginName,
           String password) {

      this(id, firstName, lastName, loginName, hashPassword(password));

   }

   /**
    * Constructs a new Employee with an already-calculated password hash
    *
    * @param id
    * @param firstName
    * @param lastName
    * @param loginName
    * @param passwordHash
    */
   public Employee(long id, String firstName, String lastName, String loginName,
           byte[] passwordHash) {
      super(id, firstName, lastName);
      this.loginName = loginName;

      if (passwordHash.length > 0) {
         this.passwordHash = passwordHash;
      } else {
         throw new IllegalArgumentException("Employees require a password");
      }

   }

   @Override
   public String getPublicDisplayName() {
      return loginName;
   }

   /**
    * Compares the provided password string against the saved password
    *
    * @param password string to test
    * @return true if the password matches, otherwise false
    */
   @Override
   public boolean validatePassword(String password) {

      if (password != null) {
         try {
            MessageDigest md = MessageDigest.getInstance(PASSWORD_ALGORITHM);

            md.update(password.getBytes());
            return MessageDigest.isEqual(passwordHash, md.digest());

         } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, "Could not validate password due to internal failure", ex);
         }

      }

      return false;
   }

   /**
    * Returns the login name used for authentication
    *
    * @return the login name
    */
   @Override
   public String getLoginName() {
      return loginName;
   }

   /**
    * Creates the password hash from a string
    *
    * @param password the password string
    * @return the password hash
    */
   private static byte[] hashPassword(String password) {

      byte[] hashedPasswd = {};

      if (password == null || password.length() == 0) {
         throw new IllegalArgumentException("Password cannot be empty.");
      }

      try {
         MessageDigest md = MessageDigest.getInstance(PASSWORD_ALGORITHM);
         md.update(password.getBytes());
         hashedPasswd = md.digest();
      } catch (NoSuchAlgorithmException ex) {
         logger.log(Level.SEVERE, "Could not hash password due to internal failure", ex);
      }

      return hashedPasswd;
   }

}
