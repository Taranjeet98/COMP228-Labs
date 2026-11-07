public class Librarian extends LibraryStaff {
    private final int dailyIssueLimit;
    private final int booksProcessed;

    public Librarian(String personId, String name, String phoneNumber, String staffId, String department, double monthlySalary, int dailyIssueLimit, int booksProcessed) {
        super(personId, name, phoneNumber, staffId, department, monthlySalary);
        if (dailyIssueLimit < 0 || booksProcessed < 0) {
            throw new IllegalArgumentException("Issue limit and books processed cannot be negative");
        }
        this.dailyIssueLimit = dailyIssueLimit;
        this.booksProcessed = booksProcessed;
    }

    @Override
    public double calculateMonthlyCompensation() {
        return getMonthlySalary() + (booksProcessed * 2.0);
    }

    public double calculateLateFee(int daysOverdue) {
        if (daysOverdue < 0) {
            System.out.println("Days overdue cannot be negative");
            return 0.0;
        }
        return daysOverdue * 1.0;
    }

    public double calculateLateFee(int daysOverdue, double dailyRate) {
        if (daysOverdue < 0 || dailyRate < 0) {
            System.out.println("Days overdue and daily rate cannot be negative");
            return 0.0;
        }
        return daysOverdue * dailyRate;
    }

    @Override
    public String getRoleDescription() {
        return "Librarian";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("Daily Issue Limit: " + dailyIssueLimit + "\nBooks Processed: " + booksProcessed + "\nTotal Compensation: $" + calculateMonthlyCompensation());
    }
}
