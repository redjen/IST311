package dao;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class FileEmployeeDaoTest {
   // TODO develop this aftrr code review and merge

   public static void main(String[] args) throws EmployeeDaoException, NoSuchAlgorithmException, IOException {
      EmployeeDao dao = EmployeeDaoFactory.getEmployeeDao();
      System.out.println(dao.getEmployeeByLoginName("test1").validatePassword("testpass1"));
   }



}
