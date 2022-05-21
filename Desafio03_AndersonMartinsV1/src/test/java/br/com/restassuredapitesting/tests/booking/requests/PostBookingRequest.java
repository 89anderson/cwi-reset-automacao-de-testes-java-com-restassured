package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.CreateBookingPayload;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    CreateBookingPayload createBookingPayload = new CreateBookingPayload();

    @Step("Cria uma reserva")
    public Response createBooking() {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                    .body(CreateBookingPayload.payloadToCreateBooking().toString())
                        .post("booking");

    }

    @Step("Cria uma reserva com parâmetros fixos")
    public Response createEspecificBooking() {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                    .body(CreateBookingPayload.payloadToCreateSpecificBooking().toString())
                        .post("booking");

    }

    @Step("Cria uma reserva utilizando um payload invalido")
    public Response criaReservaComPayloadInvalido() {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                    .body(CreateBookingPayload.payloadInvalidoParaCriarReserva().toString())
                        .post("booking");

    }

    @Step("Cria uma reserva utilizando um payload com parametros extras")
    public Response criaReservaComPayloadDeParametrosExtras() {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                    .body(CreateBookingPayload.payloadParaCriarReservaComParametroExtra().toString())
                        .post("booking");

    }

}
