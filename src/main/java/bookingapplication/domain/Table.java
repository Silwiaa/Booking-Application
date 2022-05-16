package bookingapplication.domain;

public enum Table {
    BOOKINGS("BOOKINGS"),
    FACILITIES("FACILITIES"),
    CUSTOMERS("CUSTOMERS"),
    OWNERS("OWNERS");
    private String resource;

    Table(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
