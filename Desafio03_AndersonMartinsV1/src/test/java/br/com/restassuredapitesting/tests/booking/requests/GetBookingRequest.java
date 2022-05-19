package br.com.restassuredapitesting.tests.booking.requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    public Response bookingReturnIds() {
        return given()
                            .when()
                            //.log().all()
                            .get("booking");
    }

    public Response retornaReservaDoId(int id) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking/"+id);
    }
}
