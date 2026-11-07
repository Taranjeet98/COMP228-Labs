public abstract class LibraryStaff extends Person {
    private final String staffId;
    private final String department;
    private final double monthlySalary;
    private static int staffCount = 0;

    public LibraryStaff(String personId, String name, String phoneNumber, String staffId, String department, double monthlySalary) {
        super(personId, name, phoneNumber);
        if (staffId == null || department == null) {
            throw new IllegalArgumentException("Staff ID and Department cannot be empty");
        }
        if (monthlySalary < 0) {
            throw new IllegalArgumentException("Monthly salary cannot be negative");
        }
        this.staffId = staffId;
        this.department = department;
        this.monthlySalary = monthlySalary;
        staffCount++;
    }

    public static int getStaffCount() {
        return staffCount;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }
    public abstract double calculateMonthlyCompensation();

    public void displayStaffDetails() {
        displayBasicDetails();
        System.out.println("Staff ID: " + staffId + "\nDepartment: " + department + "\nMonthly Base Salary: $" + monthlySalary);
    }
}
