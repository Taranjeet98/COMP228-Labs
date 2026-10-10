public class Person {
    private String personId;
    private String name;
    private int age;

    public Person(String personId, String name, int age) {
        this.personId = personId;
        this.name = name;
        this.age = age;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void performDuties() {
        System.out.println(name + " is doing the regular hospital duties.");
    }
}
