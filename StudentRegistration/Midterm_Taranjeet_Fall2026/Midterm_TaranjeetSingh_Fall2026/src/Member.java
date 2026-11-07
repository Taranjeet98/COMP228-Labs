public class Member extends Person implements Reportable, Reservable{
    private final String memberNumber;
    private final int age;
    private final String membershipType;
    private double outstandingFees;
    
    public Member(String personId, String name, String phoneNumber, String memberNumber, int age, String membershipType) {
        super(personId, name, phoneNumber);
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be greater than zero.");
        }
        if (name == null || memberNumber == null || membershipType == null) {
            throw new IllegalArgumentException("Required fields cannot be empty.");
        }
        this.memberNumber = memberNumber;
        this.age = age;
        this.membershipType = membershipType;
        this.outstandingFees = 0.0;
    }

    public void registerMember() {
        System.out.println("Basic registration completed for member " + getName() + " and their member number is " + memberNumber + "");
    }

    public void registerMember(String membershipType) {
        System.out.println("Member " + getName() + " registered with " + membershipType + " membership type");
    }

    @Override
    public String getRoleDescription() {
        return "Library Member";
    }

    @Override
    public void generateReport() {
        System.out.println("Account activity report for member " + getName());
    }

    @Override
    public void reserveBook(String bookId) {
        System.out.println("Member " + getName() + " successfully reserved their book with ID " + bookId);
    }

    public void displayMemberDetails() {
        displayBasicDetails();
        System.out.println("Member Number: " + memberNumber + "\nAge: " + age + "\nMembership Type: " + membershipType + "\nOutstanding Fees: $" + outstandingFees);
        System.out.println("Role: " + getRoleDescription() + "\n");
    }
}
