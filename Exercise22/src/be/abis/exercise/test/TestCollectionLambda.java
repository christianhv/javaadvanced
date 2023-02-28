package be.abis.exercise.test;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestCollectionLambda {

    public static void main(String[] args) {
        CourseRepository cr = new MemoryCourseRepository();
        List<Course> allCourses = cr.findAllCourses();

        System.out.println("All courses:");
        allCourses.forEach(System.out::println);

        System.out.println("\nSorted by title:");
        allCourses.sort(Comparator.comparing(Course::getTitle));
        allCourses.sort((c1,c2)->c1.getTitle().compareTo(c2.getTitle()));
        allCourses.forEach(System.out::println);

        System.out.println("\nSorted by duration and price:");
        //allCourses.sort((c1,c2)->(int)((c1.getDailyPrice()-c2.getDailyPrice())*1000));
        allCourses.sort(Comparator.comparingInt(Course::getDays).thenComparingDouble(Course::getDailyPrice));
        allCourses.forEach(System.out::println);

        System.out.println("\nRemove short courses:");
        allCourses.removeIf(c->c.getDays()<3);
        allCourses.forEach(System.out::println);


        System.out.println("\nPrice per course:");
        Map<String,Double> pricesPerCourse = new HashMap<>();
        for (Course c: allCourses){
            pricesPerCourse.putIfAbsent(c.getTitle(),c.getDailyPrice());
        }

        for (String key: pricesPerCourse.keySet()){
            pricesPerCourse.computeIfPresent(key,(k,v)->v*1.1);
        }
        //OR
        //pricesPerCourse.forEach((k,v)->pricesPerCourse.computeIfPresent(k,(key,val)->v*1.1));

        pricesPerCourse.forEach((k,v)-> System.out.println(k + ":" +v));

    }
}
