package hospital;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author maximdumont
 */
public class PatientCollection{
    private List<Patient> patients = new ArrayList<>();
    
    public PatientCollection(){
        
    }
    
    public PatientCollection(List<Patient> patients){
        this.patients = this.patients;
    }
    
    public void add(Patient p){
        patients.add(p);
    }
    
    public void add(Patient[] patients){
        for(Patient p : patients){
            this.patients.add(p);
        }
    }
    
    public void remove(Patient p){
        patients.remove(p);
    }
    
    public void remove(Patient[] p){
        for(Patient patient:p){
            patients.remove(p);
        }
    }
    
    public boolean contains(Patient p){
        return patients.contains(p);
    }
}
