package hospital;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Account class represents a hospital account that can log in and access
 * patient data.
 */
public class Account extends Person implements Authenticatable {

   private final String loginName;
   private final byte[] passwordHash;

   private final static String PASSWORD_ALGORITHM = "MD5";
   private boolean employee = true;

   private final static Logger logger = Logger.getLogger("Account");

   /**
    * Constructs a new Account using a plain-text password
    *
    * The password is hashed on object creation and persisted.
    *
    * @param id unique identifier
    * @param loginName the name
    * @param firstName
    * @param lastName
    * @param password
    * @param isEmployee
    */
   public Account(long id, String firstName, String lastName, String loginName,
           String password, boolean isEmployee) {

      this(id, firstName, lastName, loginName, hashPassword(password), isEmployee);

   }

   /**
    * Constructs a new Account with an already-calculated password hash
    *
    * @param id
    * @param firstName
    * @param lastName
    * @param loginName
    * @param passwordHash
    * @param isEmployee
    */
   public Account(long id, String firstName, String lastName, String loginName,
           byte[] passwordHash, boolean isEmployee) {
      super(id, firstName, lastName);
      this.loginName = loginName;
      this.employee = isEmployee;

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
    * {@inheritDoc }
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
    * {@inheritDoc }
    */
   @Override
   public String getLoginName() {
      return loginName;
   }

   public boolean isEmployee() {
      return employee;
   }

   public void setEmployee(boolean account) {
      this.employee = account;
   }

   public byte[] getPasswordHash() {
      return passwordHash;
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
