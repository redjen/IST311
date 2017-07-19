package dao;

import hospital.Account;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The FileAccountDaoImpl class is a simple implementation of an account
 * database using a pre-generated data file. It provides methods for loading,
 * searching, and updating the database.
 *
 * This class should not be instantiated directly, but instead by using the
 * AccountDaoFactory.
 *
 * @see AccountDaoFactory
 *
 */
public class FileAccountDaoImpl implements AccountDao {

   private final static String ACCOUNT_DATA = "build/accounts.dat";
   private final static String TEST_DATA = "resources/accounts.txt";

   /* A collection that emulates an account table indexed by login name */
   private final HashMap<String, Account> accountsByLoginName;

   /* A collection that emulates an account table indexed by ID */
   private final HashMap<Long, Account> accountsById;

   /* A collection that emulates an account table */
   private final ArrayList<Account> accounts;

   /**
    * Constructs a new Dao and loads the accounts
    *
    * @throws dao.DaoException if the account data file is missing,
    * empty, or cannot be parsed
    */
   public FileAccountDaoImpl() throws DaoException {

      this.accounts = new ArrayList<>();
      this.accountsByLoginName = new HashMap<>();
      this.accountsById = new HashMap<>();

      // Read all accounts from data file. If the data file is missing a new
      // one will be created using test data from resources/accounts.txt
      loadRepository();
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public Account getAccountByLoginName(String loginName) {
      return accountsByLoginName.get(loginName);
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public Account getAccountById(long id) {
      return accountsById.get(id);
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public ArrayList<Account> getAccounts() {
      ArrayList<Account> accountList = new ArrayList<>(accounts);
      return accountList;
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public void saveAccount(Account account) {
      if (accountsById.containsKey(account.getId())) {
         accountsById.put(account.getId(), account);
      } else {
         accounts.add(account);
         accountsById.put(account.getId(), account);
         accountsByLoginName.put(account.getLoginName(), account);
      }
      saveAccounts();
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public void saveAccounts() {
      int numRecords = accounts.size();

      try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(ACCOUNT_DATA)))) {
         dos.writeInt(numRecords);
         for (Account account : accounts) {
            writeAccount(account, dos);
         }
         dos.flush();
      } catch (IOException ex) {
         Logger.getLogger(FileAccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * {@inheritDoc }
    */
   @Override
   public void reset() {
      accounts.clear();
      accountsById.clear();
      accountsByLoginName.clear();

      File file = new File(ACCOUNT_DATA);
      file.delete();
   }

   /**
    * Helper method to read a single account's data from file
    *
    * @param dis
    * @return
    * @throws IOException
    */
   private Account readAccount(DataInputStream dis) throws IOException {

      long id = dis.readLong();
      String loginId = dis.readUTF();
      String firstName = dis.readUTF();
      String lastName = dis.readUTF();
      byte[] passwordHash = new byte[16];
      dis.read(passwordHash);
      boolean isEmployee = dis.readBoolean();

      return new Account(id, firstName, lastName, loginId, passwordHash, isEmployee);
   }

   /**
    * Helper method to write a single account's data to file
    *
    * @param account
    * @param dos
    * @throws IOException
    */
   private void writeAccount(Account account, DataOutputStream dos) throws IOException {

      dos.writeLong(account.getId());
      dos.writeUTF(account.getLoginName());
      dos.writeUTF(account.getFirstName());
      dos.writeUTF(account.getLastName());
      dos.write(account.getPasswordHash());
      dos.writeBoolean(account.isEmployee());
   }

   /**
    * Creates the hashmap indexes of accounts by ID and login name
    */
   private void indexAccounts() {
      accountsById.clear();
      accountsByLoginName.clear();
      for (Account account : accounts) {
         accountsById.put(account.getId(), account);
         accountsByLoginName.put(account.getLoginName(), account);
      }
   }

   /**
    * Loads the account repository from the data file
    *
    * @throws DaoException
    */
   private void loadRepository() throws DaoException {

      File data = new File(ACCOUNT_DATA);

      if (!data.exists()) {
         loadTestData();
      }

      try (DataInputStream dis = new DataInputStream(new FileInputStream(data))) {

         int numRecords = dis.readInt();

         for (int i = 0; i < numRecords; i++) {
            Account account = readAccount(dis);
            accounts.add(account);
         }

      } catch (FileNotFoundException ex) {
         throw new DaoException(String.format("Employee data %s missing.",
                 ACCOUNT_DATA));
      } catch (IOException ex) {
         throw new DaoException(String.format("Error reading account data %s.",
                 ACCOUNT_DATA), ex);
      }

      indexAccounts();
   }

   /**
    * Helper method to create the account database from a test file if it's
    * missing
    */
   private void loadTestData() {
      System.out.println("Writing test data");
      accounts.clear();

      try (Scanner in = new Scanner(new File(TEST_DATA))) {
         while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.charAt(0) != '#') {
               String[] tokens = line.split(",");
               boolean isEmployee = (Integer.parseInt(tokens[5]) == 1);
               Account account = new Account(Long.parseLong(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], isEmployee);
               accounts.add(account);
            }
         }
         saveAccounts();
      } catch (FileNotFoundException ex) {
         Logger.getLogger(FileAccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

}
