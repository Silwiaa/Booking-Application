package bookingapplication.service;

import bookingapplication.connector.DbManager;
import bookingapplication.connector.SqlFileReader;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;

@Service
@AllArgsConstructor
public class DbManagerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbManagerService.class);
    private final DbManager dbManager = DbManager.INSTANCE;
    private final SqlFileReader sqlFileReader;

    public void createQueryFromFile(String resourceName) throws SQLException {
        String sqlQuery = sqlFileReader.readFile(resourceName);
        excecuteQuery(sqlQuery);

    }

    public void createClearTableQuery(String tableName) throws SQLException {
       String sqlQuery = "DELETE FROM " + tableName;
        excecuteQuery(sqlQuery);

    }

    private void excecuteQuery(String sqlQuery) throws SQLException {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            statement.execute(sqlQuery);
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLException();
        }
    }
}
