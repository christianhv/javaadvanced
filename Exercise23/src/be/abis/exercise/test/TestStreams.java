package be.abis.exercise.test;

import be.abis.exercise.model.Person;
import be.abis.exercise.repository.MemoryPersonRepository;
import be.abis.exercise.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestStreams {

    public static void main(String[] args) {

            PersonRepository pr = new MemoryPersonRepository();
            List<Person> persons = pr.findAllPersons();
            ArrayList<Person> newPersons = new ArrayList<>();

            System.out.println("All persons in file");
            persons.forEach(System.out::println);

            System.out.println("\n--------------------- Exercise A -------------------");
            persons.stream()
                    .filter(p -> p.getLastName().startsWith("S"))
                    .sorted(Comparator.comparing(Person::getFirstName))
                    .forEach(System.out::println);

            System.out.println("\n--------------------- Exercise B -------------------");
            persons.stream()
                    .filter(p -> p.getCompany() != null)
                    .map(p -> p.getCompany().getName())
                    .distinct()
                    .forEach(System.out::println);


            System.out.println("\n--------------------- Exercise C -------------------");
            long count = persons.stream()
                    .filter(p -> p.getCompany() != null)
                    .filter(p -> p.getCompany().getAddress().getTown().equalsIgnoreCase("Leuven"))
                    .count();
            System.out.println("There are " + count + " persons working in Leuven");

            System.out.println("\n--------------------- Exercise D -------------------");
            Person youngest = persons.stream()
                    .min((p1, p2) -> p1.getBirthDate().compareTo(p2.getBirthDate()))
                    .orElse(null);
            ;
            System.out.println(youngest);

            System.out.println("\n--------------------- Exercise E -------------------");
            Map<String, List<Person>> personsPerCompany = persons.stream()
                    .filter(p -> p.getCompany() != null)
                    .collect(Collectors.groupingBy(p -> p.getCompany().getName()));
            System.out.println(personsPerCompany + "\n");
            for (String cName : personsPerCompany.keySet()) {
                System.out.println(cName + ": ");
                for (Person p : personsPerCompany.get(cName)) {
                    System.out.println(p);
                }
                System.out.println("");
            }

            System.out.println("\n--------------------- Exercise F -------------------");
            Map<String, Long> numberOfPersonsPerCompany =
                    persons.stream()
                            .filter(p -> p.getCompany() != null)
                            .collect(Collectors.groupingBy(p -> p.getCompany().getName(), Collectors.counting()));
            for (String cName : numberOfPersonsPerCompany.keySet()) {
                System.out.println(cName + ": " + numberOfPersonsPerCompany.get(cName));
            }

            System.out.println("\n--------------------- Exercise G -------------------");
            double average = numberOfPersonsPerCompany.values()
                    .stream()
                    .mapToLong(v->v)
                    .average().getAsDouble();
            System.out.println("Average nr of employees: " + average);


    }
}
