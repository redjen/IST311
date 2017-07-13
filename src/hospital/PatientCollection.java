package hospital;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author maximdumont
 */
public class PatientCollection{
    private HashMap<String,Patient> patients = new HashMap<>();
    
    public void add(Patient p){
        patients.put(p.getPatientId(), p);
    }
    
    public void remove(String patientId){
        if(patients.containsKey(patientId)){
            patients.remove(patientId);
        }
    }
    
    public Patient find(String patientId){
        if(patients.containsKey(patientId)){
            return patients.get(patientId);
        }
        return null;
    }
}
