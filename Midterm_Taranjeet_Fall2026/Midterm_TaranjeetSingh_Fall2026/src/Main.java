public class Main {
    public static void main(String[] args) {
        Member member1 = new Member("P001", "Taran", "4372287456", "M100", 27, "Standard");
        Member member2 = new Member("P002", "Nitin", "5143789672", "M101", 22, "Student");

        member1.registerMember();
        member1.registerMember("Premium");
        member1.generateReport();
        member1.reserveBook("BK492");
        
        System.out.println("\n");
        member2.displayMemberDetails();
        Librarian librarian = new Librarian("P003", "Diksha", "4785555921", "S001", "Public services", 3000.0, 50, 40);
        
        SeniorLibrarian seniorLib = new SeniorLibrarian("P004", "Ravi", "5510404438", "S002", "Administration", 4500.0, 100, 60, "Rare Books", 500.0);
        
        LibraryAssistant assistant = new LibraryAssistant("P005", "palak", "6370549605", "S003", "Technical Services", 2200.0, "Afternoon Shift", 300.0);

        librarian.displayStaffDetails();
        System.out.println();
        
        seniorLib.displayStaffDetails();
        System.out.println();
        
        assistant.displayStaffDetails();

        int days = 5;
        System.out.println("Late fee: $" + librarian.calculateLateFee(days));
        System.out.println("Late fee with custom rate: $" + librarian.calculateLateFee(days, 1.5));


        LibraryStaff staffRef;

        staffRef = librarian;
        System.out.println("\n----Reference to Librarian----");
        System.out.println("Role: " + staffRef.getRoleDescription() + "\nCompensation: $" + staffRef.calculateMonthlyCompensation());

        staffRef = seniorLib;
        System.out.println("\n----Reference to Senior Librarian----");
        System.out.println("Role: " + staffRef.getRoleDescription() + "\nCompensation: $" + staffRef.calculateMonthlyCompensation());

        staffRef = assistant;
        System.out.println("\n----Reference to Library Assistant----");
        System.out.println("Role: " + staffRef.getRoleDescription() + "\nCompensation: $" + staffRef.calculateMonthlyCompensation());

        System.out.println("Total members and staff created: " + Person.getPersonCount());
        System.out.println("Total Staff Members Created: " + LibraryStaff.getStaffCount());
    }
}
