public class MainDriver {
    public static void main(String[] args) {
        Doctor dr = new Doctor("D001", "Dr. Nitin", 40, "Cardiology", 150.0);
        Nurse nurse = new Nurse("N001", "Taran", 28, "ICU", "Night");
        Surgeon surgeon = new Surgeon("S001", "Dr. Rajesh", 45, "Neurology", 300.0, "Brain Surgery", "OR-3");
        EmergencyDoctor erDoc = new EmergencyDoctor("E001", "Dr. Joy", 35, "Emergency Medicine", 200.0, "Unit-5");

        System.out.println("\nDisplaying Details");
        dr.diagnosePatient();
        nurse.assistPatient();
        surgeon.performSurgery();
        erDoc.handleEmergency();

        System.out.println("\nMethod Overriding");
        dr.performDuties();
        nurse.performDuties();
        surgeon.performDuties();
        erDoc.performDuties();

        System.out.println("\nPolymorphism");
        Person person;

        person = dr;
        person.performDuties();

        person = nurse;
        person.performDuties();

        person = surgeon;
        person.performDuties();

        person = erDoc;
        person.performDuties();

        System.out.println("\nInterface");
        erDoc.prescribeMedication();
        erDoc.generateBill();
    }
}
