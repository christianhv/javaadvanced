package be.abis.exercise.test;

import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;
import be.abis.exercise.util.Laptop;

public class TestLaptop {

    public static void main(String[] args) {
        CourseRepository cr = new MemoryCourseRepository();
        Laptop.callPrinter(cr.findAllCourses());
    }
}
