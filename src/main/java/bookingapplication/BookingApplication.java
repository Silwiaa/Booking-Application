package bookingapplication;

import bookingapplication.processor.DbProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class BookingApplication {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BookingApplication.class, args);
        DbProcessor.cleanDb();
        DbProcessor.process();
    }
}
