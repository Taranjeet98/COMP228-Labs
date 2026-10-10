public class Doctor extends Person{
    private String specialization;
    private double consultationFee;

    public Doctor(String personId, String name, int age, String specialization, double consultationFee) {
        super(personId, name, age);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public void diagnosePatient() {
        System.out.println(getName() + " is diagnosing a patient in " + specialization + ".");
    }

    @Override
    public void performDuties() {
        System.out.println(getName() + " is looking at patients and looking on lab results.");
    }
}
