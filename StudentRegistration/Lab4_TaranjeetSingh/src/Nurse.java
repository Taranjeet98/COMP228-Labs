public class Nurse extends Person {
    private String department;
    private String shift;

    public Nurse(String personId, String name, int age, String department, String shift) {
        super(personId, name, age);
        this.department = department;
        this.shift = shift;
    }

    public void assistPatient() {
        System.out.println(getName() + " is helping patients in the " + department + " department.");
    }

    @Override
    public void performDuties() {
        System.out.println(getName() + " is monitoring medical conditions during the " + shift + " shift.");
    }
}
