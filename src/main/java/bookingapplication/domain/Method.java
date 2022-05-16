package bookingapplication.domain;

public enum Method {
    PUT("PUT"),
    POST("POST");
    private String resource;

    Method(String resource) {
        this.resource =  resource;
    }

    public String getResource() {
        return resource;
    }
}
