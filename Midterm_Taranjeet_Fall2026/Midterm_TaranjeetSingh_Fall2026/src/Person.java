public abstract class Person {
    private final String personId;
    private final String name;
    private final String phoneNumber;
    private static int personCount = 0;

    public Person(String personId, String name, String phoneNumber) {
        this.personId = personId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        personCount++;
    }

    public String getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static int getPersonCount() {
        return personCount;
    }

    public void displayBasicDetails() {
        System.out.println("Person ID: " + personId + "\nName: " + name + "\nPhone: " + phoneNumber);
    }

    public abstract String getRoleDescription();

    @Override
    public String toString() {
        return "Person [ID=" + personId + "\nName=" + name + "\nPhone=" + phoneNumber + "]";
    }
}
