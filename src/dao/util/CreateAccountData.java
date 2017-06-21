package dao.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates the test employee data file
 *
 */
public class CreateAccountData {

   public static void main(String[] args) {
      File file = new File("resources/employees.dat");
      
      try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {

         // 3 users
         dos.writeInt(3);

         // mkravitz / testpass1
         dos.writeUTF("test1");
         dos.writeLong(100);
         dos.writeUTF("Mairead Âviâja");
         dos.writeUTF("Kravitz");
         dos.write(hashPassword("testpass1"));

         // cstruna / testpass 2
         dos.writeUTF("test2");
         dos.writeLong(101);
         dos.writeUTF("Cathryn");
         dos.writeUTF("Struna");
         dos.write(hashPassword("testpass2"));

         // cstruna / testpass 2
         dos.writeUTF("test3");
         dos.writeLong(102);
         dos.writeUTF("Tina");
         dos.writeUTF("Wallace");
         dos.write(hashPassword("testpass3"));

      } catch (FileNotFoundException ex) {
         Logger.getLogger(CreateAccountData.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
         Logger.getLogger(CreateAccountData.class.getName()).log(Level.SEVERE, null, ex);
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(CreateAccountData.class.getName()).log(Level.SEVERE, null, ex);
      } 
   }

   private static byte[] hashPassword(String password) throws NoSuchAlgorithmException {

      byte[] hashedPasswd = {};

      if (password == null || password.length() == 0) {
         throw new IllegalArgumentException("Password cannot be empty.");
      }

      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(password.getBytes());
      hashedPasswd = md.digest();

      return hashedPasswd;
   }
}
