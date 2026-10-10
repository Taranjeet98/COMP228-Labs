public class EmergencyDoctor extends Doctor implements MedicalProfessional, Billable {
    private String ambulanceUnit;

    public EmergencyDoctor(String personId, String name, int age, String specialization, double consultationFee, String ambulanceUnit) {
        super(personId, name, age, specialization, consultationFee);
        this.ambulanceUnit = ambulanceUnit;
    }

    public void handleEmergency() {
        System.out.println(getName() + " is handling an emergency case with unit " + ambulanceUnit + ".");
    }

    @Override
    public void prescribeMedication() {
        System.out.println(getName() + " is writing medicines for emergency drugs.");
    }

    @Override
    public void generateBill() {
        System.out.println(getName() + " is creating the cost for the emergency visit.");
    }

    @Override
    public void performDuties() {
        System.out.println(getName() + " is providing care to ER patients and trauma emergencies.");
    }
}
