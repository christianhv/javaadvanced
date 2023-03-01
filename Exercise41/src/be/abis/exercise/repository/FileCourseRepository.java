package be.abis.exercise.repository;

import be.abis.exercise.model.Course;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FileCourseRepository implements CourseRepository {

    private List<Course> courses = new ArrayList<>();
    private String fileLocation = "/temp/javacourses/courses.csv";

    public FileCourseRepository() throws IOException {
        courses = Files.lines(Paths.get(fileLocation))
                .map(line->this.parseCourse(line))
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findAllCourses() {
        return courses;
    }

    @Override
    public void addCourse(Course c) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileLocation), Charset.forName("UTF-8"), StandardOpenOption.APPEND)) {
              bw.write(this.formatCourse(c)+"\n");
             courses.add(c);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String formatCourse(Course c) {
        StringBuilder sb = new StringBuilder("");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
        sb.append(c.getTitle()).append(";")
                .append(c.getDays()).append(";")
                .append(c.getDailyPrice()).append(";")
                .append(c.getReleaseDate().format(dtf));
        return sb.toString();
    }

    @Override
    public void printAllCourses(String language) {
        Locale l = new Locale(language);
        String baseName = "be.abis.exercise.resources.applicationResources";
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, l);
        String overview = bundle.getString("overview");
        String title = bundle.getString("title");
        String totalprice = bundle.getString("totalprice");
        String date = bundle.getString("date");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%40s\n", overview);
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-30s %-25s %-25s \n", title, totalprice,date);
        System.out.println("---------------------------------------------------------------------");
        courses.sort((c1,c2)->(int)((c1.calculatePriceWithVAT()-c2.calculatePriceWithVAT())*100));
        for (Course c : courses){
            double totalPrice= c.calculatePriceWithVAT();
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("nl","BE"));
            //nf.setMaximumFractionDigits(2);
            nf.setGroupingUsed(false);
            String formattedNumber = nf.format(totalPrice).replaceAll("\u00A0","");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy",l);
            System.out.printf("%-30s %-25s %-25s \n", c.getTitle(), formattedNumber,c.getReleaseDate().format(dtf));

        }
    }

    private Course parseCourse(String s){
        String[] vals = s.split(";");
        String title = vals[0];
        int days = Integer.parseInt(vals[1]);
        double price = Double.parseDouble(vals[2]);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate released = LocalDate.parse(vals[3],dtf);
        Course c = new Course(title,days,price,released);
        return c;
    }
}
