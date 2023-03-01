import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class TestDateTime {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        //A
        LocalDate future = now.plusDays(15).plusMonths(2).plusYears(3);
        System.out.println("Future date= "
                + future.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        //B
        LocalDate birthDay = LocalDate.of(1978, 04, 10);
        System.out.println("Je bent geboren op een "
                + birthDay.format(DateTimeFormatter.ofPattern("EEEE",new Locale("pl"))) + ".");

        //C
        LocalDate nextBirthDay = LocalDate.of(2023,birthDay.getMonth().getValue(),birthDay.getDayOfMonth());
        long daysToGo = ChronoUnit.DAYS.between(now, nextBirthDay);
        System.out.println("There are " + daysToGo
                + " days to go until your next birthday.");

        //D
        long daysOld = ChronoUnit.DAYS.between(birthDay, now);
        System.out.println("You are " + daysOld + " days old.");
    }
}
