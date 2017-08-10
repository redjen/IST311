package notifcation;

import hospital.Patient;
import hospital.PatientStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maximdumont
 * 
 * Notifcation Service to be used to send messages to a sepcific output system
 */
public class NotificationQueueingService {
    
    //Acts as the master queue to be used to keep track of patients and their changes
    private Map<String, Patient> patientsMap;

    public NotificationQueueingService() {
        this.patientsMap = new HashMap<>();
    }

    //subscribe a group of patients to be notified when changes occur
    public void subscribeAll(Patient[] patients) {
        for (Patient p : patients) {
            subscribe(p);
        }
    }

    //subscribe single patient to changes
    public void subscribe(Patient patient) {
        patientsMap.putIfAbsent(patient.getPatientId(), patient);
    }

    //modify patient status in existing patient based on identifier
    public void change(String patientIdentifier, PatientStatus status) {
        if (patientsMap.containsKey(patientIdentifier)) {
            patientsMap.get(patientIdentifier).setStatus(status);
        }
    }

    //modify patient status in existing patient based on string
    public void change(String patientIdentifier, String status) {
        if (patientsMap.containsKey(patientIdentifier)) {
            patientsMap.get(patientIdentifier).setStatus((PatientStatus.valueOf(status)));
        }
    }

    //publish all changes to out
    public void publish() {
        for (String identifier : patientsMap.keySet()) {
          System.out.println(String.format("%s has changed to %s", identifier, patientsMap.get(identifier).getStatusDisplayText()));
        }
        clearMap();
    }

    //publish all changes for exitsing identifier to out
    public void publish(String identifier) {
        if (patientsMap.containsKey(identifier)) {
            System.out.println(String.format("%s has changed to %s", identifier, patientsMap.get(identifier).getStatusDisplayText()));
            clearMap(identifier);
        }
    }

    //clear map changes for selected identifier
    private void clearMap(String identifier) {
        patientsMap.remove(identifier);
    }

    //clear entier map and start over
    private void clearMap() {
        patientsMap.clear();
    }
}
