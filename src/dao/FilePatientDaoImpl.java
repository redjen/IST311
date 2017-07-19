package dao;

import hospital.Patient;
import java.util.ArrayList;

/**
 * The FilePatientDaoImpl class represents
 * 
 */
public class FilePatientDaoImpl implements PatientDao {

   @Override
   public ArrayList<Patient> getAllActivePatients() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public ArrayList<Patient> getAllArchivedPatients() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Patient getPatientById(String patientId) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Patient getPatientById(String patientId, boolean includeArchived) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void archivePatient(Patient patient) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void savePatient(Patient patient) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

}
