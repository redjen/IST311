package hospital;

/**
 * The Person class provides attributes for any type of person in the record
 * system and methods to interact with it.
 * 
 */
public abstract class Person {

   private final long id;
   private String firstName;
   private String lastName;

   public Person(long id, String firstName, String lastName) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
   }
   
   /**
    * Returns the full name (firstname lastname)
    * @return the full name
    */
   public String getFullName() {
      return String.format("%s %s", firstName, lastName);
   }
   
   /**
    * Returns the full name with the last name first (lastname, firstname)
    * @return the full name, last name first
    */
   public String getFullNameLastFirst() {
      return String.format("%s, %s", lastName, firstName);
   }
   
   /**
    * Returns a name that can be viewed publicly
    * @return public display name
    */
   public abstract String getPublicDisplayName();

   public long getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

}
