package dao;

/**
 * Generates AccountDao instances
 *
 * This factory class should be used to access account data instead of calling
 * an implementation of AccountDao directly.
 *
 * Usage:
 *    AccountDao dao = AccountDaoFactory.getDao();
 *
 * This pattern will simplify development going forward as we will only need
 * to change this class if we switch to another storage method instead of 
 * having to change multiple classes.
 * 
 * @see AccountDao
 * @see AccountDaoException
 *
 */
public class AccountDaoFactory {

   /**
    * Specifies that the file-based dao should be used
    */
   public static final int FILE_DAO = 0;

   /**
    * Specifies that the database dao should be used (future use)
    */
   public static final int DATABASE_DAO = 1;

   /**
    * DAO to use if none is specified
    */
   private static final int DEFAULT_DAO = 0;

   public AccountDaoFactory() {
   }

   /**
    * Gets a new AccountDao of the default type
    * @return new AccountDao
    * @throws AccountDaoException 
    */
   public static AccountDao getDao() throws AccountDaoException {
      return getDao(DEFAULT_DAO);
   }

      /**
    * Gets a new AccountDao of the specified type
    * 
    * The available types are public static fields of this class: DATABASE_DAO and FILE_DAO
    * 
    * @param daoType the DaoType 
    * @return new AccountDao
    * @throws AccountDaoException 
    */
   public static AccountDao getDao(int daoType) throws AccountDaoException {
      AccountDao dao = null;

      switch (daoType) {
         case DATABASE_DAO:
            // no-op: this is for future use
            break;
         case FILE_DAO:
         default:
            dao = new FileAccountDaoImpl();
      }

      return dao;
   }
}
