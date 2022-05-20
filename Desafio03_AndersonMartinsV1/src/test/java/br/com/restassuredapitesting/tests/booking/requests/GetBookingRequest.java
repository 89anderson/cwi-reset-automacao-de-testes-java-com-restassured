package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna uma lista com as ids das reservas existentes")
    public Response bookingReturnIds() {
        return given()
                            .when()
                            //.log().all()
                            .get("booking");
    }

    @Step("Retorna uma reserva específica através de um id")
    public Response retornaReservaDoId(int id) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking/"+id);
    }
}
