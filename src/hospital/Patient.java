package hospital;

import java.util.Date;

/**
 *
 * @author maximdumont
 */
public class Patient {
    public Patient(String firstName,String lastName,Date admittanceDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.admissionDate = admittanceDate;
    }    
    
    private Date admissionDate;
    private String firstName;
    private String lastName;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the admissionDate
     */
    public Date getAdmissionDate() {
        return admissionDate;
    }

    /**
     * @param admissionDate the admissionDate to set
     */
    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }
}
