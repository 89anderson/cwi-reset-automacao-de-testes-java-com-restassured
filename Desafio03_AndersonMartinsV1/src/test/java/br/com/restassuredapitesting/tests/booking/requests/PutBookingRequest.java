package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.Utils.Utils;
import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    public Response updateBookingComToken(int id, String token) {


        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+id);
    }

    public Response updateBookingComBasicAuth(int id) {


        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .auth().preemptive().basic(Utils.USERNAME, Utils.PASSWORD)
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+id);
    }

    public Response updateBookingSemToken(int id) {


        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+id);
    }
}
