package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import br.com.restassuredapitesting.tests.booking.payloads.CreateBookingPayload;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    CreateBookingPayload createBookingPayload = new CreateBookingPayload();

    public Response createBooking() {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .log().all()
                .body(createBookingPayload.payloadToCreateBooking().toString())
                .post("booking");

    }

    public Response buscaPayloadInvalido() {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .log().all()
                .body(createBookingPayload.payloadInvalidoParaCriarReserva().toString())
                .post("booking");

    }

    public Response buscaPayloadComParametrosExtra() {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .log().all()
                .body(createBookingPayload.payloadParaCriarReservaComParametroExtra().toString())
                .post("booking");

    }

}
