public class SeniorLibrarian extends Librarian {
    private final String specialization;
    private final double seniorityAllowance;

    public SeniorLibrarian(String personId, String name, String phoneNumber, String staffId, String department, double monthlySalary, int dailyIssueLimit, int booksProcessed, String specialization, double seniorityAllowance) {
        super(personId, name, phoneNumber, staffId, department, monthlySalary, dailyIssueLimit, booksProcessed);
        
        if (specialization == null || seniorityAllowance < 0) {
            throw new IllegalArgumentException("Invalid specialization or seniority allowance.");
        }

        this.specialization = specialization;
        this.seniorityAllowance = seniorityAllowance;
    }

    @Override
    public double calculateMonthlyCompensation() {
        return super.calculateMonthlyCompensation() + seniorityAllowance;
    }

    @Override
    public String getRoleDescription() {
        return "Senior Librarian";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("Specialization: " + specialization + "\nSeniority Allowance: $" + seniorityAllowance);
    }
}
