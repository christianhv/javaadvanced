package be.abis.exercise.test;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;
import be.abis.exercise.util.Laptop;

import java.util.List;

public class TestLaptop {

    public static void main(String[] args) {
        CourseRepository cr = new MemoryCourseRepository();
        List<Course> courses = cr.findAllCourses();
        Laptop.callPrinter(courses);
    }
}
