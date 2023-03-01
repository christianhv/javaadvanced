package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;
import be.abis.exercise.repository.MemoryPersonRepository;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.util.Printer;

public class TestPrinter {

    public static void main(String[] args) {
        Printer<Person> personPrinter = new Printer<>();
        Printer<Course> coursePrinter = new Printer<>();
        PersonRepository pr = new MemoryPersonRepository();
        CourseRepository cr = new MemoryCourseRepository();

        System.out.println("Print 1");
        try {
            personPrinter.print(pr.findPersonById(1));
        } catch (PersonNotFoundException e) {
            throw new RuntimeException(e);
        }

        coursePrinter.print(cr.findAllCourses().get(1));


    }
}
