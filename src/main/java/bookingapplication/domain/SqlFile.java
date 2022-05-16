package bookingapplication.domain;

public enum SqlFile {
    OWNERS("owners.sql"),
    FACILITIES("facilities.sql"),
    CUSTOMERS("customers.sql"),
    BOOKINGS("bookings.sql");
    private String resource;

    SqlFile(String resource) {
        this.resource = "sqlFiles/" + resource;
    }

    public String getResource() {
        return resource;
    }
}
