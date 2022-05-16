package bookingapplication.processor;

import bookingapplication.connector.SqlFileReader;
import bookingapplication.domain.SqlFile;
import bookingapplication.domain.Table;
import bookingapplication.service.DbManagerService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DbProcessor {
    private static final DbManagerService dbManagerService = new DbManagerService(new SqlFileReader());

    public static void cleanDb() throws SQLException {
        dbManagerService.createClearTableQuery(Table.BOOKINGS.getResource());
        dbManagerService.createClearTableQuery(Table.FACILITIES.getResource());
        dbManagerService.createClearTableQuery(Table.OWNERS.getResource());
        dbManagerService.createClearTableQuery(Table.CUSTOMERS.getResource());

    }

    public static void process() throws SQLException {
        dbManagerService.createQueryFromFile(SqlFile.OWNERS.getResource());
        dbManagerService.createQueryFromFile(SqlFile.FACILITIES.getResource());
        dbManagerService.createQueryFromFile(SqlFile.CUSTOMERS.getResource());
        dbManagerService.createQueryFromFile(SqlFile.BOOKINGS.getResource());
    }
}