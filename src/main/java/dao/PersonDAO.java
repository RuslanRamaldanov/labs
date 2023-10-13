package dao;
import Entity.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PersonDAO {
    private static PersonDAO instance = new PersonDAO();

    private ArrayList<Person> listOfPersons;
    private FileWriter fileWriter;
    private FileReader fileReader;

    public PersonDAO() {
        listOfPersons = new ArrayList<>();
        File file = new File("Data.txt");
        if(file.exists()) {
            try {
                fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                ArrayList<String> dataStrings = new ArrayList<>();
                while (line != null) {
                    String dataLine = line;
                    line = bufferedReader.readLine();
                    String name = dataLine.split(":")[0];
                    String[] tmp = dataLine.split(" ");
                    ArrayList<String> numbers = new ArrayList<>(Arrays.asList(tmp).subList(1, tmp.length));
                    listOfPersons.add(new Person(name, numbers));
                }
                fileReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addPerson(Person personToAdd) {
        try {
            fileWriter = new FileWriter("Data.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(personToAdd.getName()).append(": ");
        try {
            fileWriter.write(sb.toString());
            fileWriter.close();
            listOfPersons.add(personToAdd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePerson(Person personToDelete) {
        listOfPersons.remove(personToDelete);
    }
    public static PersonDAO getInstance() {
        return instance;
    }
    public ArrayList<Person> getListOfPersons() {
        return listOfPersons;
    }
    public Person getPerson(String name) {
        if(listOfPersons != null && !listOfPersons.isEmpty()) {
            for (Person listOfPerson : listOfPersons) {
                if (listOfPerson.getName().equals(name))
                    return listOfPerson;
            }
        }
        return null;
    }
    public void refreshFile() {
        try {
            fileWriter = new FileWriter("Data.txt", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        boolean firstIteration = true;
        for (Person listOfPerson : listOfPersons) {
            if(firstIteration)
                sb.append(listOfPerson.getName()).append(": ");
            else
                sb.append("\n").append(listOfPerson.getName()).append(":");

            for (int j = 0; j < listOfPerson.getPhoneNumbers().size(); j++) {
                sb.append(" ").append(listOfPerson.getPhoneNumbers().get(j));
            }
            firstIteration = false;
        }
        try {
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
