package bookingapplication.parser;

import bookingapplication.exception.InvalidDateFormatException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateParser {
    private final static String patternDate = "yyyy-MM-dd";
    public LocalDate parseDate(String date) throws Exception {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(patternDate));
        } catch (Exception e) {
            throw new InvalidDateFormatException();
        }
    }
}
