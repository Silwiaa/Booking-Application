package bookingapplication.parser;

import bookingapplication.exception.InvalidDateFormatException;
import bookingapplication.service.DbManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateParser.class);
    private final static String patternDate = "yyyy-MM-dd";
    public LocalDate parseDate(String date) throws Exception {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(patternDate));
        } catch (Exception e) {
            LOGGER.error("Date needs accord to pattern yyyy-MM-dd");
            throw new InvalidDateFormatException();
        }
    }
}
