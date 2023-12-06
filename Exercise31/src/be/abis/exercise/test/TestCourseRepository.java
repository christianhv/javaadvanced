package be.abis.exercise.test;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;

import java.util.List;

public class TestCourseRepository {

    public static void main(String[] args) {
        CourseRepository cr = new MemoryCourseRepository();

       // List<Course> allCourses = cr.findAllCourses();

       // System.out.println(cr.formatCourse(allCourses.get(0)));

       // allCourses.forEach(c->System.out.println(cr.formatCourse(c)));

        cr.printAllCoursesCSV();



    }
}
