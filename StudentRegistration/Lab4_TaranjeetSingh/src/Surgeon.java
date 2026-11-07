public class Surgeon extends Doctor{
    private String surgeryType;
    private String operatingRoom;

    public Surgeon(String personId, String name, int age, String specialization, double consultationFee, String surgeryType, String operatingRoom) {
        super(personId, name, age, specialization, consultationFee);
        this.surgeryType = surgeryType;
        this.operatingRoom = operatingRoom;
    }

    public void performSurgery() {
        System.out.println(getName() + " is performing " + surgeryType + " surgery in " + operatingRoom + ".");
    }

    @Override
    public void performDuties() {
        System.out.println(getName() + " carried out routine surgeries and follow-up tests.");
    }
}
