package Entity;
import java.util.ArrayList;

public class Person {
    private String name;
    private ArrayList<String> phoneNumbers;

    public Person(String name) {
        this.name = name;
        phoneNumbers = new ArrayList<>();
    }
    public Person(String name, ArrayList<String> phoneNumbers) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    public void addPhoneNumber(String phoneNumber) {
        phoneNumbers.add(phoneNumber);
    }
}
