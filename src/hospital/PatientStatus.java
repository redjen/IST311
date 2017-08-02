package hospital;

/**
 * PatientStatus defines the states a patient can be in.
 *
 * @author redjen
 */
public enum PatientStatus {
   WAITING("waiting"),
   TREATMENT("in treatment"),
   RECOVERY("in recovery"),
   DISCHARGE("ready");
   
   private final String displayText;

   private PatientStatus(String displayText) {
      this.displayText = displayText;
   }

   public String getDisplayText() {
      return displayText;
   }
   
}
