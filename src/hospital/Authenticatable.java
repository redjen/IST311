package hospital;

/**
 * The interface Authenticatable provides authentication functions for Persons
 * that can log on to the system.
 * 
 */
public interface Authenticatable{
   
   /**
    * Returns the login name used for authentication
    * @return the login name
    */
   public String getLoginName();
   
   /**
    * Validates the provided password against the stored password
    * @param password the password to validate
    * @return true if matched, otherwise false
    */
   public boolean validatePassword(String password);
}
