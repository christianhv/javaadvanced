package be.abis.exercise.junittest;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.MemoryPersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemoryPersonRepositoryTest {

    PersonRepository pr;

    @BeforeEach
    public void setUp(){
        pr=new MemoryPersonRepository();
    }

    @Test
    void thereAre9PersonsInMemory() {
       int nrOfPersons = pr.findAllPersons().size();
       assertEquals(9,nrOfPersons);
    }

    @Test
    void personWithId1IsSandy() throws PersonNotFoundException {
        assertEquals("Sandy",pr.findPersonById(1).getFirstName());
    }

    @Test
    void personWithId100ThrowsException() {
        assertThrows(PersonNotFoundException.class,()->pr.findPersonById(100));
    }

    @Test
    void personByEmailPasswordIsKoen() throws PersonNotFoundException {
        assertEquals("Koen",pr.findPersonByEmailAndPassword("kdebacker@abis.be","somepass2").getFirstName());
    }

    @Test
    void personWithWrongPwdThrowsException() {
        assertThrows(PersonNotFoundException.class,()->pr.findPersonByEmailAndPassword("kdebacker@abis.be","somepass3"));
    }

    @Test
    void thereAre4PersonsForAbis() throws PersonNotFoundException {
        assertEquals(4,pr.findPersonsForCompany("abis").size());
    }

    @Test
    void personsForABCcompanyThrowsException() {
        assertThrows(PersonNotFoundException.class,()->pr.findPersonsForCompany("abc"));
    }

    @Test
    void addPersonWorks() throws PersonAlreadyExistsException {
        Address a = new Address("Diestsevest","32 bus 4b","3000","Leuven","België","B");
        Company c = new Company("Abis",a);
        Person p1 = new Person("Emily","Mees", LocalDate.of(1990,02,5),"emees@abis.be","abis345","nl",c);

        int personsBefore=pr.findAllPersons().size();
        pr.addPerson(p1);
        int personsAfter=pr.findAllPersons().size();
        assertEquals(1,personsAfter-personsBefore);
    }

    @Test
    void addExistingPersonThrowsException() throws PersonAlreadyExistsException {
        Person p2 = new Person("Sam","Schillebeeckx", LocalDate.of(1983,12,15),"sschillebeeckx@abis.be","abis365","fr");
       assertThrows(PersonAlreadyExistsException.class,()->pr.addPerson(p2));
    }


}