package bookingapplication.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum DbManager {
    INSTANCE;
    private Connection connection;

    DbManager() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "booking_user");
        connectionProps.put("password", "booking_Pass123");
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/booking" +
                            "?serverTimezone=Europe/Warsaw" +
                            "&useSSL=False", connectionProps);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
