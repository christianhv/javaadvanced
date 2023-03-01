package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.repository.MemoryPersonRepository;
import be.abis.exercise.repository.PersonRepository;

public class TestCalculateAge {

    public static void main(String[] args) throws PersonNotFoundException {
        PersonRepository pr = new MemoryPersonRepository();
        System.out.println(pr.findPersonById(1).calculateAge());
    }
}
