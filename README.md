Simple REST API Booking Application with web-services to:
- add new booking
- update booking
- get bookings for given customer
- get bookings for given facility

To call example services from Windows Command Prompt use curl commands:

curl -X POST http://localhost:8080/api/v1/booking/createBooking -H "Content-Type: application/json" -d "{\"fromDate\": \"2022-06-02\", \"toDate\": \"2022-06-05\", \"price\": \"0\", \"facilityId\": \"1\", \"customerId\": \"1\", \"ownerId\": \"1\"}"

curl -X PUT -H "Content-Type: multipart/form-data;" -F "bookingId=1" -F "dateFrom=2022-07-02" -F "dateTo=2022-07-09" "http://localhost:8080/api/v1/booking/updateBooking"

curl http://localhost:8080/api/v1/booking/getCustomerBookings?customerName=Tommy%20Atkins

curl http://localhost:8080/api/v1/booking/getBookings?facilityId=1
