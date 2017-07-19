package dao;

import app.ViewManager;
import hospital.Patient;
import javafx.collections.ListChangeListener;

/**
 * The PatientChangeObserver class is a listener for use with PatientCollection.
 * It passes the changes off to the PatientDao for processing.
 *
 * If you want to change the storage method we use in the app change the dao
 * type in the constructor method.
 *
 * Usage:
 *
 * Attach it to the patient list object. This should be done in the main
 * controller based on our architecture, but it can be used by any controller.
 * It should always be attached before objects are added to the list.
 *
 * @see ViewManager
 * @see PatientDao
 *
 * @author redjen
 *
 */
public class PatientChangeObserver implements ListChangeListener<Patient> {

   private final PatientDao dao;

   public PatientChangeObserver() throws DaoException {
      this.dao = DaoFactory.getPatientDao();
   }

   /**
    * Change handler for the observed list. It calls the PatientDAO to update
    * the data stored on disk.
    *
    * @param changes
    */
   @Override
   public void onChanged(Change<? extends Patient> changes) {
      while (changes.next()) {
         
         // we only care about adding, updating, and deleting patients. The
         // list ordering is not important so ignore wasPermuted
         if (changes.wasAdded()) {
            for (Patient patient : changes.getAddedSubList()) {
               dao.savePatient(patient);
            }
         }

         if (changes.wasUpdated()) {
            for (int i = changes.getFrom(); i < changes.getTo(); i++) {
               dao.savePatient(changes.getList().get(i));
            }
            System.out.println(changes.getFrom() + " to " + changes.getTo());
         }

         if (changes.wasRemoved()) {
            for (Patient patient : changes.getRemoved()) {
               dao.archivePatient(patient);
            }
         }
      }
   }

}
