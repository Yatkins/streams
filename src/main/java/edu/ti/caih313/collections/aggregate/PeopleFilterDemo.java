package edu.ti.caih313.collections.aggregate;

import edu.ti.caih313.collections.dataobj.EmailAddress;
import edu.ti.caih313.collections.dataobj.Name;
import edu.ti.caih313.collections.dataobj.Person;

import java.util.*;
import java.util.stream.Stream;

import static edu.ti.caih313.collections.dataobj.Person.Gender.*;

public class PeopleFilterDemo {
    public static void main(String... args) {

        Person johnSmith = new Person(new Name("John", "Smith"), MALE, 42);
        johnSmith.setEmail(EmailAddress.Email.HOME, "johnsmith@gmail.com");
        Person karlNg = new Person(new Name("Karl", "Ng"), MALE, 73);
        karlNg.setEmail(EmailAddress.Email.HOME, "karlng@gmail.com");
        karlNg.setEmail(EmailAddress.Email.SCHOOL, "karlngschool@gmail.com");
        Person jeffSmith = new Person(new Name("Jeff", "Smith"), MALE, 21);
        jeffSmith.setEmail(EmailAddress.Email.WORK, "jeffsmithwork@gmail.com");
        Person tomRich = new Person(new Name("Tom", "Rich"), MALE, 18);
        tomRich.setEmail(EmailAddress.Email.WORK, "tomrichwork@gmail.com");
        tomRich.setEmail(EmailAddress.Email.SCHOOL, "tomrichschool@gmail.com");
        tomRich.setEmail(EmailAddress.Email.HOME, "tomrich@gmail.com");
        Person bobSmith = new Person(new Name("Bob", "Smith"), MALE, 13);
        bobSmith.setEmail(EmailAddress.Email.HOME, "bobsmith@gmail.com");
        Person janeDoe = new Person(new Name("Jane", "Doe"), FEMALE, 27);
        janeDoe.setEmail(EmailAddress.Email.HOME, "janedoe@gmail.com");
        janeDoe.setEmail(EmailAddress.Email.WORK, "janedoework@gmail.com");
        Person tonyStark = new Person(new Name("Tony", "Stark"), MALE, 52);
        tonyStark.setEmail(EmailAddress.Email.WORK, "tonystarkwork@gmail.com");
        tonyStark.setEmail(EmailAddress.Email.HOME, "tonystark@gmail.com");
        tonyStark.setEmail(EmailAddress.Email.SCHOOL, "tonystarkschool@gmail.com");
        Person boPeep = new Person(new Name("Bo", "Peep"), FEMALE, 205);
        boPeep.setEmail(EmailAddress.Email.WORK, "bopeep@gmail.com");

        Person[] personArray = {
                johnSmith, karlNg, jeffSmith, tomRich, bobSmith, janeDoe, tonyStark, boPeep
        };

        System.out.println("All persons");
        Arrays.stream(personArray).forEach(e -> System.out.println(e.getName()));

        System.out.print("\n");
        long numOver20 = Arrays.stream(personArray).filter(p -> p.getAge() > 20).count();
        System.out.println("Number of persons older than 20: " + numOver20);

        System.out.print("\n");
        System.out.println("All persons older than 20");
        Arrays.stream(personArray)
                .filter(p -> p.getAge()>20)
                .forEach(e -> System.out.println(e.getName()));

        System.out.print("\n");
        System.out.println("All " + MALE + " persons");
        Arrays.stream(personArray)
                .filter(p -> p.getGender()==MALE)
                .forEach(p -> System.out.println(p.getName()));

        System.out.print("\n");
        OptionalDouble averageFemaleAge =
                Arrays.stream(personArray)
                .filter(p -> p.getGender()==FEMALE)
                .mapToInt(Person::getAge) // <=> mapToInt(p -> p.getAge())
                .average();
        if (averageFemaleAge.isPresent()) {
            System.out.println("Average age of  "
                    + FEMALE + " persons = "
                    + averageFemaleAge.getAsDouble());
        } else {
            System.out.println("Average age of  "
                    + FEMALE + " persons is not available.");
        }

        System.out.print("\n");
        System.out.println("All persons in age order");
        Arrays.stream(personArray)
                .sorted((p1,p2) -> (p1.getAge() - p2.getAge()))
                .forEach(e -> System.out.println(e.getName()));

        System.out.print("\n");
        System.out.println("First four last names uppercased.");
        Stream<String> fourLastNamesUpperStream =
                Arrays.stream(personArray)
                .map(p -> p.getName().getLastName().toUpperCase())
                .sorted()
                .limit(4);
        fourLastNamesUpperStream.forEach(s -> System.out.print(s + ", "));

        System.out.println("\n");
        System.out.println("The youngest female is:");
        Stream<Name> femaleAge =
                Arrays.stream(personArray)
                        .filter(p -> p.getGender() == FEMALE)
                        .sorted((p1, p2) -> p1.getAge() - p2.getAge()).map(Person::getName).limit(1);
        femaleAge.forEach(System.out::print);

        System.out.print("\n");
        System.out.print("\n");
        System.out.println("The oldest male is:");
        Stream<Name> maleAge =
                Arrays.stream(personArray)
                        .filter(p -> p.getGender() == MALE)
                        .sorted((p1, p2) -> p2.getAge() - p1.getAge()).map(Person::getName).limit(1);
        maleAge.forEach(System.out::print);

        System.out.print("\n");
        System.out.print("\n");
        System.out.println("List of unique last names:");
        Stream  people =
                Arrays.stream(personArray).map(p -> p.getName().getLastName()).distinct()
                ;
        people.forEach(s -> System.out.print(s + ", "));

        System.out.println("\n");
       System.out.println("Number of members in each family:");
        Map<String, Integer> distinctFam = new HashMap<String, Integer>();
        Stream<String> families =
                Arrays.stream(personArray).map(p -> p.getName().getLastName());
        ArrayList<String> familyArray = new ArrayList<>();
        families.forEach(familyArray::add);
        for (String s : familyArray) {
            if (distinctFam.containsKey(s)) {
                distinctFam.put(s, distinctFam.get(s) + 1);
            } else {
                distinctFam.put(s, 1);
            }
        }
        System.out.println(distinctFam);

        System.out.println("\n");
        System.out.println("All school email addresses");
        Arrays.stream(personArray).filter(p -> p.getEmailMap().containsKey(EmailAddress.Email.SCHOOL))
                .map(p -> p.getEmailMap().get(EmailAddress.Email.SCHOOL))
                .forEach(System.out::println);

        System.out.println("\n");
        System.out.println("All email addresses for those over 40");
        Arrays.stream(personArray).filter(p -> p.getAge() > 40)
                .map(p -> p.getEmailMap().values())
                .forEach(System.out::println);

        System.out.println("\n");
        System.out.println("Each name, and number of email addresses; ordered by number of email address, last name, then first name"); //cant figure out how to sort by two variables in one stream
        Arrays.stream(personArray).sorted((p1, p2) -> p1.getEmailMap().size() - p2.getEmailMap().size())
                .forEach(e-> System.out.println(e.getName() + ": " + e.getEmailMap().size()));

        System.out.println("\n");
        System.out.println("Each name, and number of email addresses; ordered by number of email address, last name, then first name");
        Arrays.sort(personArray, Person::compareByNumEmails); //sorted by email
        Person[] sorted = new Person[8];
        int i = 0;
        int j = 0;
        while(personArray[i].getEmailMap().size() == j){    //sort each email num group by name
            sorted[i] = personArray[i];
            i++;
            Arrays.sort(sorted, Person::compareByName);
            j++;
        } //still not fully sorted
        Arrays.stream(personArray).forEach(e-> System.out.println(e.getName() + ": " + e.getEmailMap().size()));
    }
}