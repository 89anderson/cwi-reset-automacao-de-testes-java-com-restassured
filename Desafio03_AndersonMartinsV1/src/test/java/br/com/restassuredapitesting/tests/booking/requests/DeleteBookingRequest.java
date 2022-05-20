package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    @Step("Deleta uma reserva específica utilizando um token")
    public Response deleteBooking(int id, String token) {

        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", token)
                .when()
                    .delete("booking/"+id);
    }

    @Step("Deleta uma reserva específica sem utilizar um token")
    public Response deleteBookingSemToken(int id) {

        return given()
                .header("Content-Type", "application/json")
                .when()
                .delete("booking/"+id);
    }

}
